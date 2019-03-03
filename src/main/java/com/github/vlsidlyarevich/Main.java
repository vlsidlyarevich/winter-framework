package com.github.vlsidlyarevich;

import com.github.vlsidlyarevich.test.AnotherServiceBean;
import com.github.vlsidlyarevich.winterframework.context.ApplicationContext;
import com.github.vlsidlyarevich.winterframework.context.SimpleApplicationContext;

/**
 * Bootstrap application class, starting the general application context factory.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new SimpleApplicationContext("com.github.vlsidlyarevich.test");
        AnotherServiceBean bean = (AnotherServiceBean) context.getBean("anotherServiceBean");
        bean.callMethod();
    }
}
