package cn.arry.myspringboot.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//要标记启动类为配置类
@Configuration
//告知要扫描的包路径，未指定包路径的情况下扫描注解标注类所在的包路径
@ComponentScan
public @interface MySpringBootApplication {

}
