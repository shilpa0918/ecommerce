package com.docker.spring.app.exception;

import org.eclipse.jetty.io.QuietException;

public class ProductNotFoundException extends QuietException.RuntimeException {
    String message;

    public ProductNotFoundException(String message) {
        this.message = message;
    }
}
