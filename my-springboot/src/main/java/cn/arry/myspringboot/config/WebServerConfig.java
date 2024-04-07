package cn.arry.myspringboot.config;

import cn.arry.myspringboot.WebServer;
import org.apache.catalina.Context;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@Configuration
public class WebServerConfig {


    /**
     * 创建tomcat
     *
     * @return
     */
    @Bean
    public WebServer tomcatWebServer(){
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(8081);

        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost("localhost");

        StandardHost host = new StandardHost();
        host.setName("localhost");

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        return null;
    }

    private WebServer createWebServer(){

        return null;
    }
}
