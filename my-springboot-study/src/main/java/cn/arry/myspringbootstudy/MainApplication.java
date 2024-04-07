package cn.arry.myspringbootstudy;

import cn.arry.myspringboot.MySpringBoot;
import cn.arry.myspringboot.anno.MySpringBootApplication;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@MySpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        MySpringBoot.run(MainApplication.class,args);
    }
}
