package cn.arry.myspringboot;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

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
        createWebServer(webApplicationContext);
    }

    private static void createWebServer(WebApplicationContext webApplicationContext){
        WebServer server = webApplicationContext.getBean(WebServer.class);
        server.start(webApplicationContext);
    }
}
