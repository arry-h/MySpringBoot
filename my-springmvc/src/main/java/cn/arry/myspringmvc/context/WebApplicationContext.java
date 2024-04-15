package cn.arry.myspringmvc.context;

import org.springframework.context.ApplicationContext;

/**
 * @author arry
 * @description web
 * @since 1.0
 */
public interface WebApplicationContext extends ApplicationContext {
    String ROOT_NAME = WebApplicationContext.class.getName() + "ROOT";
}
