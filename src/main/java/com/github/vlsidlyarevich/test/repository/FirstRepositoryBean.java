package com.github.vlsidlyarevich.test.repository;

import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

@Repository
public class FirstRepositoryBean {

    public void callRepoMethod() {
        System.out.println(this.getClass().getSimpleName() + " is called");
    }
}
