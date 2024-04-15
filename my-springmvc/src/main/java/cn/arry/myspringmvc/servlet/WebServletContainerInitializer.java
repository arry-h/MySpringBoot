package cn.arry.myspringmvc.servlet;

import cn.arry.myspringmvc.context.WebApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@HandlesTypes(WebApplicationInitializer.class)
public class WebServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext ctx) throws ServletException {
        if (!ObjectUtils.isEmpty(webAppInitializerClasses)) {
            ArrayList<WebApplicationInitializer> initializers = new ArrayList<>();
            for (Class<?> aClass : webAppInitializerClasses) {
                //不是接口 && 不是抽象类 && 可以转换
                if (!aClass.isInterface() && !Modifier.isAbstract(aClass.getModifiers())
                        && WebApplicationInitializer.class.isAssignableFrom(aClass)) {

                    try {
                        initializers.add((WebApplicationInitializer) ReflectionUtils.accessibleConstructor(aClass).newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!initializers.isEmpty()) {
                for (WebApplicationInitializer initializer : initializers) {
                    initializer.onStartUp(ctx);
                }
            }
        }
    }
}
