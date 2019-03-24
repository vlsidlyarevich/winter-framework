package com.github.vlsidlyarevich.winterframework.beans.factory.support;

/**
 * Exception which is thrown when class scan error occurs.
 */
public class ClassScanException extends RuntimeException {

    /**
     * Instantiates a new Class scan exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ClassScanException(String message, Throwable cause) {
        super(message, cause);
    }
}
