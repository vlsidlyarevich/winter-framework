package com.github.vlsidlyarevich.winterframework.beans.factory;

public class BeanInstantiationException extends BeanFactoryInitializationException {

    public BeanInstantiationException(Throwable cause) {
        super(cause);
    }

    public BeanInstantiationException(String message) {
        super(message);
    }
}
