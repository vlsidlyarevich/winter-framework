package com.github.vlsidlyarevich.test;

import com.github.vlsidlyarevich.test.repository.SimpleRepositoryBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.InitializingBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.context.ApplicationEventListener;
import com.github.vlsidlyarevich.winterframework.context.event.ContextClosedEvent;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;

/**
 * Another service bean.
 * Demonstrates the Field-based injection, and use of {@link InitializingBean}
 * with {@link ApplicationEventListener}.
 *
 * @see ServiceBean
 * @see SimpleRepositoryBean
 */
@Component
public class AnotherServiceBean implements InitializingBean, ApplicationEventListener<ContextClosedEvent> {

    @Autowired
    private ServiceBean serviceBean;

    /**
     * Call method.
     */
    public void callMethod() {
        System.out.println(this.getClass().getSimpleName() + " is called");
        this.serviceBean.callServiceAndRepo();
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean callback is called in "
                + this.getClass().getSimpleName() + ", " + serviceBean + " is set");
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("Context is closed");
    }
}
