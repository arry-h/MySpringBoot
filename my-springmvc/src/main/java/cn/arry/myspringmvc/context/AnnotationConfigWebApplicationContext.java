package cn.arry.myspringmvc.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author arry
 * @description 注解配置上下文容器
 * @since 1.0
 */
public class AnnotationConfigWebApplicationContext extends AbstractRefreshableWebApplicationContext implements AnnotationConfigRegistry {
    @Nullable
    private BeanNameGenerator beanNameGenerator;

    @Nullable
    private ScopeMetadataResolver scopeMetadataResolver;

    private final Set<Class<?>> componentClasses = new LinkedHashSet<>();

    private final Set<String> basePackages = new LinkedHashSet<>();

    @Override
    public void register(Class<?>... componentClasses) {
        Assert.notEmpty(componentClasses, "At least one component class must be specified");
        Collections.addAll(this.componentClasses, componentClasses);
    }

    @Override
    public void scan(String... basePackages) {
        Assert.notEmpty(basePackages, "At least one base package must be specified");
        Collections.addAll(this.basePackages, basePackages);
    }

    @Nullable
    protected BeanNameGenerator getBeanNameGenerator() {
        return this.beanNameGenerator;
    }

    @Nullable
    protected ScopeMetadataResolver getScopeMetadataResolver() {
        return this.scopeMetadataResolver;
    }


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
        AnnotatedBeanDefinitionReader reader = getAnnotatedBeanDefinitionReader(beanFactory);
        ClassPathBeanDefinitionScanner scanner = getClassPathBeanDefinitionScanner(beanFactory);

        BeanNameGenerator beanNameGenerator = getBeanNameGenerator();
        if (beanNameGenerator != null) {
            reader.setBeanNameGenerator(beanNameGenerator);
            scanner.setBeanNameGenerator(beanNameGenerator);
            beanFactory.registerSingleton(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR, beanNameGenerator);
        }

        ScopeMetadataResolver scopeMetadataResolver = getScopeMetadataResolver();
        if (scopeMetadataResolver != null) {
            reader.setScopeMetadataResolver(scopeMetadataResolver);
            scanner.setScopeMetadataResolver(scopeMetadataResolver);
        }

        if (!this.componentClasses.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Registering component classes: [" +
                        StringUtils.collectionToCommaDelimitedString(this.componentClasses) + "]");
            }
            reader.register(ClassUtils.toClassArray(this.componentClasses));
        }

        if (!this.basePackages.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Scanning base packages: [" +
                        StringUtils.collectionToCommaDelimitedString(this.basePackages) + "]");
            }
            scanner.scan(StringUtils.toStringArray(this.basePackages));
        }

        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            for (String configLocation : configLocations) {
                try {
                    Class<?> clazz = ClassUtils.forName(configLocation, getClassLoader());
                    if (logger.isTraceEnabled()) {
                        logger.trace("Registering [" + configLocation + "]");
                    }
                    reader.register(clazz);
                }
                catch (ClassNotFoundException ex) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("Could not load class for config location [" + configLocation +
                                "] - trying package scan. " + ex);
                    }
                    int count = scanner.scan(configLocation);
                    if (count == 0 && logger.isDebugEnabled()) {
                        logger.debug("No component classes found for specified class/package [" + configLocation + "]");
                    }
                }
            }
        }
    }

    protected AnnotatedBeanDefinitionReader getAnnotatedBeanDefinitionReader(DefaultListableBeanFactory beanFactory) {
        return new AnnotatedBeanDefinitionReader(beanFactory, getEnvironment());
    }

    protected ClassPathBeanDefinitionScanner getClassPathBeanDefinitionScanner(DefaultListableBeanFactory beanFactory) {
        return new ClassPathBeanDefinitionScanner(beanFactory, true, getEnvironment());
    }
}
