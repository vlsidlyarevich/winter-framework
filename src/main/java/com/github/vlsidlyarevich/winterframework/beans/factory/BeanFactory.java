package com.github.vlsidlyarevich.winterframework.beans.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    //TODO maybe registry?
    private final Map singletons = new ConcurrentHashMap<String, Object>();

    public Object getBean(final String name) {
        return singletons.get(name);
    }
}
