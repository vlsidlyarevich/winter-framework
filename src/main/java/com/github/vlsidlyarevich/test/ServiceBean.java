package com.github.vlsidlyarevich.test;

import com.github.vlsidlyarevich.test.repository.FirstRepositoryBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanNameAware;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;

/**
 * Simple service bean.
 * Demonstrates the Setter-based injection, and {@link BeanNameAware} callback functionality.
 *
 * @see AnotherServiceBean
 * @see FirstRepositoryBean
 */
@Component
public class ServiceBean implements BeanNameAware {

    private String beanName;
    private FirstRepositoryBean repositoryBean;

    /**
     * Call service and repo.
     */
    public void callServiceAndRepo() {
        System.out.println(beanName + " is called");
        this.repositoryBean.callRepoMethod();
    }

    /**
     * Sets repository bean.
     *
     * @param repositoryBean the repository bean
     */
    @Autowired
    public void setRepositoryBean(final FirstRepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    /**
     * Gets repository bean.
     *
     * @return the repository bean
     */
    public FirstRepositoryBean getRepositoryBean() {
        return repositoryBean;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
