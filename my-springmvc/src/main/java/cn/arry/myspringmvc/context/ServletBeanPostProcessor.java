package cn.arry.myspringmvc.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public class ServletBeanPostProcessor implements BeanPostProcessor {

    private ServletContext servletContext;

    private ServletConfig servletConfig;

    public ServletBeanPostProcessor(ServletContext servletContext, ServletConfig servletConfig) {
        this.servletContext = servletContext;
        this.servletConfig = servletConfig;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean != null && bean instanceof ServletContextAware){
            ((ServletContextAware) bean).serServletContext(servletContext);
        }
        if (bean != null && bean instanceof ServletConfigAware){
            ((ServletConfigAware) bean).setServletConfig(servletConfig);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
