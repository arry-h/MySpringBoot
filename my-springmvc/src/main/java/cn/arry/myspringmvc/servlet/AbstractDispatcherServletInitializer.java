package cn.arry.myspringmvc.servlet;


import cn.arry.myspringmvc.context.AbstractRefreshableWebApplicationContext;
import cn.arry.myspringmvc.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.*;

/**
 * @author arry
 * @description 创建DispatcherServlet以及初始化ioc
 * @since 1.0
 */
public abstract class AbstractDispatcherServletInitializer implements WebApplicationInitializer {


    public static final String DEFAULT_SERVLET_NAME = "dispatcher";
    public static final String DEFAULT_FILTER_NAME = "filters";
    public static final int M = 1024*1024;

    @Override
    public void onStartUp(ServletContext servletContext) {
        //创建父容器
        AnnotationConfigApplicationContext rootApplicationContext = createRootApplicationContext();
        //父容器放入servlet
        servletContext.setAttribute("ROOT_CONTEXT",rootApplicationContext);
        //刷新父容器
        rootApplicationContext.refresh();

        WebApplicationContext webApplicationContext = createWebApplicationContext();
        //创建DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(DEFAULT_SERVLET_NAME, dispatcherServlet);
        //配置
        dynamic.setLoadOnStartup(1);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(null,5*M,5*M,5);
        dynamic.setMultipartConfig(multipartConfigElement);
        dynamic.addMapping(getServletMappings());
        Filter[] servletFilters = getServletFilters();
        if (!ObjectUtils.isEmpty(servletFilters)){
            for (Filter servletFilter : servletFilters) {
                servletContext.addFilter(DEFAULT_FILTER_NAME,servletFilter);
            }
        }

    }

    /**
     * 创建父容器<p>
     * Create the "<strong>root</strong>" application context to be provided to the
     * {@code ContextLoaderListener}.
     * <p>The returned context is delegated to
     * {@link ContextLoaderListener#ContextLoaderListener(org.springframework.web.context.WebApplicationContext)} and will
     * be established as the parent context for any {@code DispatcherServlet} application
     * contexts. As such, it typically contains middle-tier services, data sources, etc.
     * @return the root application context, or {@code null} if a root context is not
     * desired
     * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer
     */
    protected abstract AnnotationConfigApplicationContext createRootApplicationContext();

    /**
     * 创建子容器
     * @return
     */
    protected abstract WebApplicationContext createWebApplicationContext();

    /**
     * 获取包扫描配置类<p>
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createRootApplicationContext() root application context}.
     * @return the configuration for the root application context, or {@code null}
     * if creation and registration of a root context is not desired
     */
    protected abstract Class<?>[] getRootConfigClasses();

    protected abstract Class<?>[] getWebConfigClasses();

    /**
     * 过滤器<p>
     * Specify filters to add and map to the {@code DispatcherServlet}.
     * @return an array of filters or {@code null}
     * @see #registerServletFilter(ServletContext, Filter)
     */
    protected abstract Filter[] getServletFilters();

    /**
     * 映射器<p>
     * Specify the servlet mapping(s) for the {@code DispatcherServlet} &mdash;
     * for example {@code "/"}, {@code "/app"}, etc.
     * @see #registerDispatcherServlet(ServletContext)
     */
    protected abstract String[] getServletMappings();
}
