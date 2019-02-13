package com.github.vlsidlyarevich.winterframework.beans.factory;

public class BeanFactoryInitializationException extends RuntimeException {

    public BeanFactoryInitializationException(String message) {
        super(message);
    }

    public BeanFactoryInitializationException(Throwable cause) {
        super(cause);
    }
}
