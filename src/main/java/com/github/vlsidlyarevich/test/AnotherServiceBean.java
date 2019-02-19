package com.github.vlsidlyarevich.test;

import com.github.vlsidlyarevich.winterframework.beans.factory.InitializingBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;

@Component
public class AnotherServiceBean implements InitializingBean {

    @Autowired
    private ServiceBean serviceBean;

    public void callMethod() {
        System.out.println(this.getClass().getSimpleName() + " is called");
        this.serviceBean.callServiceAndRepo();
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(serviceBean + " is set");
    }
}
