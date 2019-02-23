package com.github.vlsidlyarevich.winterframework.beans.factory.annotation;

import com.github.vlsidlyarevich.javax.annotation.PostConstruct;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanInstantiationException;
import com.github.vlsidlyarevich.winterframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PostConstructAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(PostConstruct.class)) continue;

            if (method.getParameterCount() > 1) {
                throw new BeanInstantiationException("Found multiple arguments on @PostConstruct method in bean:" + bean);
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                throw new BeanInstantiationException("@PostConstruct must be non static and public in bean:" + bean);
            }
            try {
                method.invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BeanInstantiationException(e);
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) {
        return bean;
    }
}
