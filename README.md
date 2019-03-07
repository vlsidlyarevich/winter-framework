# Winter Framework

My tryout to create simplified version of Spring Framework core which supports following:

* Classpath scanning for components
* Multi-staged constructor for components
* 

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
which is annotated with `@Component` thus managed by container
and implements [BeanNameAware.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/winterframework/beans/factory/BeanNameAware.java) callback. 
Also he demonstrates the setter-based injection (see TODO)
* [AnotherServiceBean.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/test/AnotherServiceBean.java)
which is annotated with `@Component` thus managed by container and implements two callbacks - [InitializingBean.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/winterframework/beans/factory/InitializingBean.java)
and [ApplicationEventListener.java](https://github.com/vlsidlyarevich/winter-framework/blob/master/src/main/java/com/github/vlsidlyarevich/winterframework/context/ApplicationEventListener.java).
Also he has property-based injection, and `@PostConstruct` lifecycle callback.
* 

[]

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

