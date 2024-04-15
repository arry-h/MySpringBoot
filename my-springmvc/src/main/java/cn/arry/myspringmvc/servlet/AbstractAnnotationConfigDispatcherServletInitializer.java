package cn.arry.myspringmvc.servlet;

import cn.arry.myspringmvc.context.AnnotationConfigWebApplicationContext;
import cn.arry.myspringmvc.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public abstract class AbstractAnnotationConfigDispatcherServletInitializer extends AbstractDispatcherServletInitializer {
    @Override
    protected AnnotationConfigApplicationContext createRootApplicationContext() {
        Class<?>[] rootConfigClasses = getRootConfigClasses();
        if (!ObjectUtils.isEmpty(rootConfigClasses)){
            AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext();
            rootContext.register(rootConfigClasses);
            return rootContext;
        } else {
            return null;
        }
    }

    @Override
    protected WebApplicationContext createWebApplicationContext() {
        Class<?>[] webConfigClasses = getWebConfigClasses();
        if (!ObjectUtils.isEmpty(webConfigClasses)){
            AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
            webApplicationContext.register(webConfigClasses);
            return webApplicationContext;
        } else {
            return null;
        }
    }



}
