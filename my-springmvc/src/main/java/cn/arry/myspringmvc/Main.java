package cn.arry.myspringmvc;

import cn.arry.myspringmvc.context.AnnotationConfigWebApplicationContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        System.out.println(context.getBean("service"));
    }
}
