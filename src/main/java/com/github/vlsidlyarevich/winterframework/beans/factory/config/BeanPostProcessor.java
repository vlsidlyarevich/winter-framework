package com.github.vlsidlyarevich.winterframework.beans.factory.config;

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String name);

    Object postProcessAfterInitialization(Object bean, String name);
}
