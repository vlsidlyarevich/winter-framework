package com.github.vlsidlyarevich.winterframework.context;

public interface ApplicationEventListener<T> {

    void onApplicationEvent(T event);
}
