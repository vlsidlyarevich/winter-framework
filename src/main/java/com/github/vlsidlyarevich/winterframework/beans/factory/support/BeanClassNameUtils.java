package com.github.vlsidlyarevich.winterframework.beans.factory.support;

/**
 * Utility class to operate with bean class names.
 */
public interface BeanClassNameUtils {

    /**
     * The constant PACKAGE_SEPARATOR.
     */
    String PACKAGE_SEPARATOR = ".";
    /**
     * The constant PATH_SEPARATOR.
     */
    String PATH_SEPARATOR = "/";
    /**
     * The constant COMPILED_CLASS_POSTFIX.
     */
    String COMPILED_CLASS_POSTFIX = ".class";

    /**
     * Remove compiled class postfix from string path.
     *
     * @param path the path
     * @return the string
     */
    static String removePostfix(final String path) {
        return path.replace(COMPILED_CLASS_POSTFIX, "");
    }

    /**
     * Convert path to packaged representation.
     *
     * @param path the path
     * @return the string
     */
    static String convertToPackagedPath(final String path) {
        return path.replaceAll(PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }

    /**
     * Convert classname to bean name.
     *
     * @param className the class name
     * @return the string
     */
    static String convertToBeanName(final String className) {
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }
}
