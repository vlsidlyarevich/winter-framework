package com.github.vlsidlyarevich.winterframework.beans.factory;

import java.util.List;

public interface BeanFactory {

    Object getBean(String name);

    List<Object> getSingletons();
}
