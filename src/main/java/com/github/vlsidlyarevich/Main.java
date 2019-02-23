package com.github.vlsidlyarevich;

import com.github.vlsidlyarevich.test.AnotherServiceBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactory;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.PostConstructAnnotationBeanPostProcessor;

/**
 * Bootstrap application class, starting the general application context factory.
 */
public class Main {

    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory();
        factory.addBeanPostProcessor(new PostConstructAnnotationBeanPostProcessor());
        factory.init("com.github.vlsidlyarevich.test");
        factory.populateProperties();
        AnotherServiceBean bean = (AnotherServiceBean) factory.getBean("anotherServiceBean");
        bean.callMethod();
    }
}
