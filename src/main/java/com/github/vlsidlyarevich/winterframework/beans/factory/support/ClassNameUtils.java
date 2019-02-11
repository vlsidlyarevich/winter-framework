package com.github.vlsidlyarevich.winterframework.beans.factory.support;

public interface ClassNameUtils {

    String PACKAGE_SEPARATOR = ".";
    String PATH_SEPARATOR = "/";
    String COMPILED_CLASS_POSTFIX = ".class";

    static String removePostfix(final String path) {
        return path.replace(COMPILED_CLASS_POSTFIX, "");
    }

    static String convertToPackagedPath(final String path) {
        return path.replaceAll(PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }

    static String convertToBeanName(final String className) {
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }
}
