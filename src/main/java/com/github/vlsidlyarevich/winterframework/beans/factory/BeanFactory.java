package com.github.vlsidlyarevich.winterframework.beans.factory;

public interface BeanFactory {

    void init(final String path);

    void close();

    Object getBean(String name);
}
