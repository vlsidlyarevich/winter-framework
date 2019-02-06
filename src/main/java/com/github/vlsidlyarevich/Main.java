package com.github.vlsidlyarevich;

import com.github.vlsidlyarevich.winterframework.beans.factory.BeanFactory;

public class Main {

    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory();
        factory.init("com.github.vlsidlyarevich.test");
    }
}
