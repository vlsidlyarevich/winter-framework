package com.github.vlsidlyarevich.winterframework.beans.factory;

import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.ClassNameUtils;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.ClassScanException;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.PathScanningClassesProvider;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;
import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

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
        singletons.forEach((k, s) -> {
            populateFields(s);
            populateSetters(s);
        });
        injectBeanNames();
        injectBeanFactories();
        afterPropertiesSet();
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

    private void injectBeanNames() {
        singletons.keySet().forEach(name -> {
            Object bean = singletons.get(name);
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(name);
            }
        });
    }

    private void injectBeanFactories() {
        for (String name : singletons.keySet()) {
            Object bean = singletons.get(name);
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }
    }

    private void afterPropertiesSet() {
        singletons.keySet().forEach(name -> {
            Object bean = singletons.get(name);
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }
        });
    }

    public Object getBean(final String name) {
        return singletons.get(name);
    }
}
