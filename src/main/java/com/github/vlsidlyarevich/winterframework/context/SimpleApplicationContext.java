package com.github.vlsidlyarevich.winterframework.context;

import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.SimpleBeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.StageAwareBeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.PostConstructAnnotationBeanPostProcessor;

public class SimpleApplicationContext implements ApplicationContext {

    private final StageAwareBeanFactory beanFactory = new SimpleBeanFactory();

    public SimpleApplicationContext(final String path) {
        beanFactory.addBeanPostProcessor(new PostConstructAnnotationBeanPostProcessor());
        beanFactory.init(path);
        beanFactory.populateProperties();
    }

    public void close() {
        this.beanFactory.close();
    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    @Override
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
