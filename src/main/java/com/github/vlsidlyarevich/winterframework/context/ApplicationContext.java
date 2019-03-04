package com.github.vlsidlyarevich.winterframework.context;

import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactory;

public interface ApplicationContext extends BeanFactory {

    BeanFactory getBeanFactory();

    void close();
}
