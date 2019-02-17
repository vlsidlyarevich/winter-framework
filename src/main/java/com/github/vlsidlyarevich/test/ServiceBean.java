package com.github.vlsidlyarevich.test;

import com.github.vlsidlyarevich.test.repository.FirstRepositoryBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanNameAware;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;

@Component
public class ServiceBean implements BeanNameAware {

    private String beanName;
    private FirstRepositoryBean repositoryBean;

    public void callServiceAndRepo() {
        System.out.println(beanName + " is called");
        this.repositoryBean.callRepoMethod();
    }

    @Autowired
    public void setRepositoryBean(final FirstRepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    public FirstRepositoryBean getRepositoryBean() {
        return repositoryBean;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
