package cn.arry.myspringboot;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public class MySpringBoot {

    public static void run(Class<?> primarySource,String... args){
        //创建容器
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        //往容器中注入配置类，否则容器就是空的
        webApplicationContext.register(primarySource);
        //启动spring容器，根据传入的启动类（配置类）扫描包路径，创建所有的bean
        webApplicationContext.refresh();
        createTomcatServer(webApplicationContext);
    }

    private static void createTomcatServer(WebApplicationContext webApplicationContext){
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

        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet(webApplicationContext));
        context.addServletMappingDecoded("/*","dispatcher");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
