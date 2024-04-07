package cn.arry.myspringboot;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@Component
public class JettyWebServer implements WebServer{
    @Override
    public void start(WebApplicationContext webApplicationContext) {
        System.out.println("Jetty start...");
    }
}
