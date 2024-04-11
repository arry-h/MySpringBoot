package cn.arry.myspringmvc.context;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public interface ConfigurableWebApplicationContext extends WebApplicationContext{

    /**
     * Set the ServletContext for this web application context.
     * <p>Does not cause an initialization of the context: refresh needs to be
     * called after the setting of all configuration properties.
     * @see #refresh()
     */
    void setServletContext(ServletContext servletContext);

    /**
     * Set the ServletConfig for this web application context.
     * Only called for a WebApplicationContext that belongs to a specific Servlet.
     * @see #refresh()
     */
    void setServletConfig(ServletConfig servletConfig);

    /**
     * Return the ServletConfig for this web application context, if any.
     */
    ServletContext getServletContext();

    /**
     * Return the ServletConfig for this web application context, if any.
     */
    ServletConfig getServletConfig();
}
