package com.github.vlsidlyarevich.test.repository;

import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactoryAware;
import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

@Repository
public class FirstRepositoryBean implements BeanFactoryAware {

    private BeanFactory beanFactory;

    public void callRepoMethod() {
        System.out.println(this.getClass().getSimpleName() + " is called");
        System.out.println(beanFactory + " is here");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
