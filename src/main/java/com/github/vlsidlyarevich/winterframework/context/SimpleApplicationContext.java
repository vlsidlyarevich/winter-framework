package com.github.vlsidlyarevich.winterframework.context;

import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.SimpleBeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.StageAwareBeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.PostConstructAnnotationBeanPostProcessor;
import com.github.vlsidlyarevich.winterframework.context.event.ContextClosedEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SimpleApplicationContext implements ApplicationContext {

    private final StageAwareBeanFactory beanFactory = new SimpleBeanFactory();

    public SimpleApplicationContext(final String path) {
        beanFactory.addBeanPostProcessor(new PostConstructAnnotationBeanPostProcessor());
        beanFactory.init(path);
        beanFactory.populateProperties();
    }

    public void close() {
        this.beanFactory.close();
        this.toggleContextClosedEvent();
    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    @Override
    public List<Object> getSingletons() {
        return beanFactory.getSingletons();
    }

    @Override
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    private void toggleContextClosedEvent() {
        for (Object singleton : this.getSingletons()) {
            for (Type type : singleton.getClass().getGenericInterfaces()) {
                if (!(type instanceof ParameterizedType)) continue;
                if (!((((ParameterizedType) type).getActualTypeArguments()[0].equals(ContextClosedEvent.class)))) {
                    continue;
                }

                try {
                    Method method = singleton.getClass().getMethod("onApplicationEvent", ContextClosedEvent.class);
                    method.invoke(singleton, new ContextClosedEvent());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
