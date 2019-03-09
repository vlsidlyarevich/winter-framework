package com.github.vlsidlyarevich.test;

import com.github.vlsidlyarevich.javax.annotation.PostConstruct;
import com.github.vlsidlyarevich.test.repository.FirstRepositoryBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.InitializingBean;
import com.github.vlsidlyarevich.winterframework.beans.factory.annotation.Autowired;
import com.github.vlsidlyarevich.winterframework.context.ApplicationEventListener;
import com.github.vlsidlyarevich.winterframework.context.event.ContextClosedEvent;
import com.github.vlsidlyarevich.winterframework.stereotype.Component;

/**
 * Another service bean.
 * Demonstrates the Field-based injection, {@link PostConstruct} callback and use of {@link InitializingBean} with {@link ApplicationEventListener}.
 *
 * @see ServiceBean
 * @see FirstRepositoryBean
 */
@Component
public class AnotherServiceBean implements InitializingBean, ApplicationEventListener<ContextClosedEvent> {

    @Autowired
    private ServiceBean serviceBean;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct is called in " + this.getClass().getSimpleName());
    }

    /**
     * Call method.
     */
    public void callMethod() {
        System.out.println(this.getClass().getSimpleName() + " is called");
        this.serviceBean.callServiceAndRepo();
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(serviceBean + " is set");
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("Context is closed");
    }
}
