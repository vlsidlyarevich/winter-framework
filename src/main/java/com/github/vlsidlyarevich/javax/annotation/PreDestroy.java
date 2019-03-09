package com.github.vlsidlyarevich.javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The <code>PreDestroy</code> annotation is used on a method as a
 * callback notification to signal that the instance is in the
 * process of being removed by the container. The method annotated
 * with <code>PreDestroy</code> is typically used to
 * release resources that it has been holding. This annotation must be
 * supported by all container-managed objects that support the use of
 * the <code>PostConstruct</code> annotation except the Java EE application
 * client. The method on which the <code>PreDestroy</code> annotation
 * is applied must fulfill all of the following criteria:
 * <ul>
 * <li>The method must not have any parameters except in the case of
 * interceptors in which case it takes an <code>InvocationContext</code>
 * object as defined by the Interceptors specification.</li>
 * <li>The method defined on an interceptor class or superclass of an
 * interceptor class must have one of the following signatures:
 * <p>
 * void &#060;METHOD&#062;(InvocationContext)
 * <p>
 * Object &#060;METHOD&#062;(InvocationContext) throws Exception
 * <p>
 * <i>Note: A PreDestroy interceptor method must not throw application
 * exceptions, but it may be declared to throw checked exceptions including
 * the java.lang.Exception if the same interceptor method interposes on
 * business or timeout methods in addition to lifecycle events. If a
 * PreDestroy interceptor method returns a value, it is ignored by
 * the container.</i>
 * </li>
 * <li>The method defined on a non-interceptor class must have the
 * following signature:
 * <p>
 * void &#060;METHOD&#062;()
 * </li>
 * <li>The method on which PreDestroy is applied may be public, protected,
 * package private or private.</li>
 * <li>The method must not be static.</li>
 * <li>The method should not be final.</li>
 * <li>If the method throws an unchecked exception it is ignored by
 * the container.</li>
 * </ul>
 *
 * @see PostConstruct
 * @see
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PreDestroy {

}
