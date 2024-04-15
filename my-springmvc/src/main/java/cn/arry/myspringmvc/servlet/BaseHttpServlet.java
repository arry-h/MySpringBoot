package cn.arry.myspringmvc.servlet;

import cn.arry.myspringmvc.context.AbstractRefreshableWebApplicationContext;
import cn.arry.myspringmvc.context.WebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public abstract class BaseHttpServlet extends HttpServlet {

    private WebApplicationContext webApplicationContext;

    public BaseHttpServlet(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ApplicationContext rootContext = (ApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_NAME);
        if (!ObjectUtils.isEmpty(webApplicationContext)){
            //需要转换
            AbstractRefreshableWebApplicationContext wac = (AbstractRefreshableWebApplicationContext) this.webApplicationContext;

            //设置父子容器
            if (wac.getParent() == null){
                wac.setParent(rootContext);
            }

            //配置上下文
            wac.setServletConfig(getServletConfig());
            wac.setServletContext(getServletContext());

            //容器刷新
            wac.refresh();
        }
        onRefresh();
    }

    protected abstract void onRefresh();
}
