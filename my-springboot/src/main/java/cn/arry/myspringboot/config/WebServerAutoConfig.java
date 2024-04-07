package cn.arry.myspringboot.config;

import cn.arry.myspringboot.JettyWebServer;
import cn.arry.myspringboot.TomcatWebServer;
import cn.arry.myspringboot.WebServer;
import cn.arry.myspringboot.condition.JettyCondition;
import cn.arry.myspringboot.condition.TomcatCondition;
import org.apache.catalina.Context;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@Configuration
public class WebServerAutoConfig {

    @Conditional(TomcatCondition.class)
    @Bean
    public WebServer tomcatWebServer(){
        return new TomcatWebServer();
    }

    @Conditional(JettyCondition.class)
    @Bean
    public WebServer JettyWebServer(){
        return new JettyWebServer();
    }
}
