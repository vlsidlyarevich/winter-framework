package com.github.vlsidlyarevich.test.repository;

import com.github.vlsidlyarevich.winterframework.beans.factory.SimpleBeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactoryAware;
import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

@Repository
public class FirstRepositoryBean implements BeanFactoryAware {

    private SimpleBeanFactory beanFactory;

    public void callRepoMethod() {
        System.out.println(this.getClass().getSimpleName() + " is called");
        System.out.println(beanFactory + " is here");
    }

    @Override
    public void setBeanFactory(SimpleBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
