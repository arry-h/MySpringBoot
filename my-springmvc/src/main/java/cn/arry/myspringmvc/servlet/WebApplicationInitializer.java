package cn.arry.myspringmvc.servlet;

import javax.servlet.ServletContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public interface WebApplicationInitializer {

    void onStartUp(ServletContext servletContext);
}
