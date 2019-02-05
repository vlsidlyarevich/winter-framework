package com.github.vlsidlyarevich;

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
