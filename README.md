# Winter Framework

My tryout to create simplified version of Spring Framework core for educational purposes.

I was inspired by [this article](https://habr.com/ru/post/419679/). 

## Supported features

* Classpath scanning for components
* Multi-staged constructor for components
* Field or setter based dependency injection
* Aware callbacks: `BeanNameAware`, `BeanFactoryAware`
* Bean post processors support, with post processing before and after initialization
* Javax bean lifecycle annotations support: `@PostConstruct`, `@PreDestroy`
* `InitializingBean` and `DisposableBean` callbacks
* Application event listening

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

The project has no dependencies, it uses only pure java, you don't need to preinstall anything except java.

``` bash 
✗ java -version
openjdk version "11.0.1" 2018-10-16
OpenJDK Runtime Environment 18.9 (build 11.0.1+13)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.1+13, mixed mode)
```

### Installing

Clone the project and open in your favourite IDE.

End with an example of getting some data out of the system or using it for a little demo

## Running

Just run [Main.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/Main.java) to test framework functionality.
Several services created fot testing purposes:

* [ServiceBean.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/test/ServiceBean.java)
which is managed by container and implements [BeanNameAware.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/winterframework/beans/factory/BeanNameAware.java) callback. 
Also he demonstrates the setter-based injection
* [AnotherServiceBean.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/test/AnotherServiceBean.java)
which is managed by container and implements two callbacks - [InitializingBean.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/winterframework/beans/factory/InitializingBean.java)
and [ApplicationEventListener.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/winterframework/context/ApplicationEventListener.java).
Also he has property-based injection, and `@PostConstruct` lifecycle callback.
* [FirstRepositoryBean.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/test/repository/FirstRepositoryBean.java)
which is managed by container and implements [BeanFactoryAware.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/winterframework/beans/factory/BeanFactoryAware.java)
callback

These testing classes are designed to demonstrate all features and provide following console output:
```
==Initializing bean factory==
==Populating bean properties==
@PostConstruct is called in AnotherServiceBean
com.github.vlsidlyarevich.test.ServiceBean@5b275dab is set
AnotherServiceBean is called
serviceBean is called
FirstRepositoryBean is called
com.github.vlsidlyarevich.winterframework.beans.factory.SimpleBeanFactory@61832929 is here
Context is closed
```

## Bean lifecycle

Framework supports following bean lifecycle:

![Bean lifecycle](https://github.com/vlsidlyarevich/winter-framework/blob/assets/Spring-Bean-Life-Cycle.jpg?raw=true)

## Contributing

This project is just my research based on [this article](https://habr.com/ru/post/419679/). No any specific rules of contribution needed.

## Authors

**Vladislav Sidlyarevich** - [Github profile](https://github.com/vlsidlyarevich)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
