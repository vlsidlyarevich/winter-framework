package com.github.vlsidlyarevich.winterframework.beans.factory;

/**
 * Interface to be implemented by beans that want to be aware of their
 * bean name in a bean factory.
 *
 * <p>For a list of all bean lifecycle methods, see the
 * {@link BeanFactory BeanFactory javadocs}.
 *
 * @see BeanFactoryAware
 * @see InitializingBean
 * @see DisposableBean
 */
public interface BeanNameAware {

    void setBeanName(String beanName);
}
