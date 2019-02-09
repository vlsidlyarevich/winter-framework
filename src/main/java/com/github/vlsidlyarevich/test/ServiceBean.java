package com.github.vlsidlyarevich.test;

import com.github.vlsidlyarevich.test.repository.RepositoryBean;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;

@Component
public class ServiceBean {

    private RepositoryBean repositoryBean;

    public void setRepositoryBean(final RepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    public RepositoryBean getRepositoryBean() {
        return repositoryBean;
    }
}
