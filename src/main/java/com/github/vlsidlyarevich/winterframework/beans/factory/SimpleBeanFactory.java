package com.github.vlsidlyarevich.winterframework.beans.factory;

import com.github.vlsidlyarevich.javax.annotation.PreDestroy;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.beans.factory.config.BeanPostProcessor;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.ClassNameUtils;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.ClassScanException;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.PathScanningClassesProvider;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;
import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory implements StageAwareBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    //TODO maybe registry?
    private final Map<String, Object> singletons = new ConcurrentHashMap<>();
    private final Map<Class<?>, Object> singletonClasses = new ConcurrentHashMap<>();

    public void init(final String path) {
        System.out.println("==Initializing bean factory==");

        final PathScanningClassesProvider classesProvider = new PathScanningClassesProvider();
        List<Class<?>> classes;
        try {
            classes = classesProvider.provideClassesForPath(path);
            //TODO filter approach?
            classes.forEach(clazz -> {
                if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Repository.class)) {
                    try {
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        this.singletons.put(ClassNameUtils.convertToBeanName(clazz.getSimpleName()), instance);
                        this.singletonClasses.put(clazz, instance);
                    } catch (ReflectiveOperationException e) {
                        throw new BeanCreationException(e);
                    }
                }
            });

        } catch (ClassScanException e) {
            throw new BeanFactoryInitializationException(e);
        }
    }

    public void populateProperties() {
        System.out.println("==Populating bean properties==");
        singletons.forEach((name, bean) -> {
            populateFields(bean);
            populateSetters(bean);

            injectBeanNames(name, bean);

            injectBeanFactories(bean);

            beanPostProcessors.forEach(bpp -> bpp.postProcessBeforeInitialization(bean, name));

            afterPropertiesSet(bean);

            beanPostProcessors.forEach(bpp -> bpp.postProcessAfterInitialization(bean, name));
        });
    }

    public void close() {
        singletons.forEach((name, bean) -> {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (!method.isAnnotationPresent(PreDestroy.class)) continue;
                if (method.getParameterCount() != 0) continue;
                if (!Modifier.isPublic(method.getModifiers())) continue;

                try {
                    method.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new BeanInstantiationException(e);
                }
            }

            destroy(bean);
        });
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        if (beanPostProcessor == null) throw new BeanFactoryInitializationException("Nullable BeanPostProcessor added");
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public Object getBean(final String name) {
        return singletons.get(name);
    }

    @Override
    public List<Object> getSingletons() {
        return new ArrayList<>(singletons.values());
    }

    private void populateFields(Object singleton) {
        for (Field field : singleton.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Autowired.class)) continue;
            Object toInject = singletonClasses.get(field.getType());
            if (toInject == null) {
                throw new BeanInstantiationException("Could not find autowire candidate for class: " + field.getDeclaringClass());
            }

            field.setAccessible(true);
            try {
                field.set(singleton, toInject);
            } catch (IllegalAccessException e) {
                throw new BeanInstantiationException(e);
            } finally {
                field.setAccessible(false);
            }
        }
    }

    private void populateSetters(final Object singleton) {
        for (Method method : singleton.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Autowired.class)) continue;
            if (method.getParameterCount() != 1) continue;
            if (!Modifier.isPublic(method.getModifiers())) continue;

            Class<?> clazz = method.getParameterTypes()[0];
            Object toInject = singletonClasses.get(clazz);
            if (toInject == null) {
                throw new BeanInstantiationException("Could not find autowire candidate for class: " + clazz);
            }

            try {
                method.invoke(singleton, toInject);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BeanInstantiationException(e);
            }
        }
    }

    private void injectBeanNames(String name, Object bean) {
        if (bean instanceof BeanNameAware) {
            ((BeanNameAware) bean).setBeanName(name);
        }
    }

    private void injectBeanFactories(Object bean) {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
    }

    private void afterPropertiesSet(Object bean) {
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
    }

    private void destroy(Object bean) {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
    }
}
