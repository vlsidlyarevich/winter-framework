package com.github.vlsidlyarevich.test.repository;

import com.github.vlsidlyarevich.javax.annotation.PostConstruct;
import com.github.vlsidlyarevich.test.AnotherServiceBean;
import com.github.vlsidlyarevich.test.ServiceBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactoryAware;
import com.github.vlsidlyarevich.winterframework.beans.factory.SimpleBeanFactory;
import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

/**
 * Simple repository bean.
 * Demonstrates {@link PostConstruct} callback and use of {@link BeanFactoryAware}.
 *
 * @see BeanFactoryAware
 * @see ServiceBean
 * @see AnotherServiceBean
 */
@Repository
public class SimpleRepositoryBean implements BeanFactoryAware {

    private SimpleBeanFactory beanFactory;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct is called in " + this.getClass().getSimpleName()
                + ", " + beanFactory + " is set");
    }

    public void callRepoMethod() {
        System.out.println(this.getClass().getSimpleName() + " is called");
    }

    @Override
    public void setBeanFactory(SimpleBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
