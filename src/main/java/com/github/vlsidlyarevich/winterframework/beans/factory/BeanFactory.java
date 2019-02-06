package com.github.vlsidlyarevich.winterframework.beans.factory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    //TODO maybe registry?
    private final Map singletons = new ConcurrentHashMap<String, Object>();

    //TODO separate class scan logic into scanner
    public void init(final String path) {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        try {
            var resources = classLoader.getResources(path.replace(".", "/"));
            resources.asIterator().forEachRemaining(url -> {


            });


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Object getBean(final String name) {
        return singletons.get(name);
    }
}
