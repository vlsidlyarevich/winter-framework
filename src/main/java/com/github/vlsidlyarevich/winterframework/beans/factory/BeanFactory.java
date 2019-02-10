package com.github.vlsidlyarevich.winterframework.beans.factory;

import com.github.vlsidlyarevich.winterframework.beans.factory.support.ClassScanException;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.PathScanningClassesProvider;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;
import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    //TODO maybe registry?
    private final Map<String, Object> singletons = new ConcurrentHashMap<>();

    public void init(final String path) {
        final PathScanningClassesProvider classesProvider = new PathScanningClassesProvider();
        List<Class<?>> classes;
        try {
            classes = classesProvider.provideClassesForPath(path);
            //TODO filter approach?
            classes.forEach(clazz -> {
                if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Repository.class)) {
                    try {
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        this.singletons.put(clazz.getSimpleName(), instance);
                    } catch (ReflectiveOperationException e) {
                        throw new BeanCreationException(e);
                    }
                }
            });

        } catch (ClassScanException e) {
            throw new BeanFactoryInitializationException(e);
        }

    }

    public Object getBean(final String name) {
        return singletons.get(name);
    }
}
