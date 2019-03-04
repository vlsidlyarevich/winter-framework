package com.github.vlsidlyarevich.winterframework.beans.factory;

import com.github.vlsidlyarevich.winterframework.beans.factory.config.BeanPostProcessor;

public interface StageAwareBeanFactory extends BeanFactory {

    void init(final String path);

    void populateProperties();

    void close();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
