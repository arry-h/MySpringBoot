package cn.arry.myspringmvc.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public interface ServletContextAware extends Aware {

    void serServletContext(ServletContext servletContext);
}
