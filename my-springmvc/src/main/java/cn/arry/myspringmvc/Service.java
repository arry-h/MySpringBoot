package cn.arry.myspringmvc;


import cn.arry.myspringmvc.context.ServletContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@Component(value = "service")
public class Service implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void serServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
