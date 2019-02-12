package com.github.vlsidlyarevich.winterframework.beans.factory;

import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.ClassNameUtils;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.ClassScanException;
import com.github.vlsidlyarevich.winterframework.beans.factory.support.PathScanningClassesProvider;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;
import com.github.vlsidlyarevich.winterframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    //TODO maybe registry?
    private final Map<String, Object> singletons = new ConcurrentHashMap<>();
    private final Map<Class<?>, Object> singletonClasses = new ConcurrentHashMap<>();

    public void init(final String path) {
        System.out.println("==Initializing bean factory==");

        final PathScanningClassesProvider classesProvider = new PathScanningClassesProvider();
        List<Class<?>> classes;
        try {
            classes = classesProvider.provideClassesForPath(path);
            //TODO filter approach?
            classes.forEach(clazz -> {
                if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Repository.class)) {
                    try {
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        this.singletons.put(ClassNameUtils.convertToBeanName(clazz.getSimpleName()), instance);
                        this.singletonClasses.put(clazz, instance);
                    } catch (ReflectiveOperationException e) {
                        throw new BeanCreationException(e);
                    }
                }
            });

        } catch (ClassScanException e) {
            throw new BeanFactoryInitializationException(e);
        }
    }

    public void populateProperties() {
        System.out.println("==Populating bean properties==");
        singletons.forEach((k, s) -> {
            for (Method method : s.getClass().getMethods()) {
                if (!method.isAnnotationPresent(Autowired.class)) continue;
                for (Class<?> clazz : method.getParameterTypes()) {

                }
            }

        });
    }

    public Object getBean(final String name) {
        return singletons.get(name);
    }
}
