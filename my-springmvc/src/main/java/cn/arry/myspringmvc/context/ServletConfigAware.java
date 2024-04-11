package cn.arry.myspringmvc.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public interface ServletConfigAware extends Aware {


    void setServletConfig(ServletConfig servletContext);
}
