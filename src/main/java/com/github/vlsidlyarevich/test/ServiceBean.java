package com.github.vlsidlyarevich.test;

import com.github.vlsidlyarevich.test.repository.SimpleRepositoryBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanNameAware;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;

/**
 * Simple service bean.
 * Demonstrates the Setter-based injection, and {@link BeanNameAware} callback functionality.
 *
 * @see AnotherServiceBean
 * @see SimpleRepositoryBean
 */
@Component
public class ServiceBean implements BeanNameAware {

    private String beanName;
    private SimpleRepositoryBean repositoryBean;

    /**
     * Call service and repo.
     */
    public void callServiceAndRepo() {
        System.out.println(this.getClass().getSimpleName() + " is called");
        this.repositoryBean.callRepoMethod();
    }

    /**
     * Sets repository bean.
     *
     * @param repositoryBean the repository bean
     */
    @Autowired
    public void setRepositoryBean(final SimpleRepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    /**
     * Gets repository bean.
     *
     * @return the repository bean
     */
    public SimpleRepositoryBean getRepositoryBean() {
        return repositoryBean;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
