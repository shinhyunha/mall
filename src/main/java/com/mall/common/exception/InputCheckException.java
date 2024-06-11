package com.mall.common.exception;

public class InputCheckException extends RuntimeException {
    public InputCheckException(String message) {
        super(message);
    }

    public InputCheckException(String message, Throwable e) {
        super(message, e);
    }
}
