package com.github.vlsidlyarevich;

import com.github.vlsidlyarevich.test.repository.FirstRepositoryBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactory;

/**
 * Bootstrap application class, starting the general application context factory.
 */
public class Main {

    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory();
        factory.init("com.github.vlsidlyarevich.test");
        FirstRepositoryBean bean = (FirstRepositoryBean) factory.getBean("repositoryBean");
        System.out.println(bean);
    }
}
