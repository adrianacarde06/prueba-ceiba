package com.btg.sistemafondos.exception;

public class ValidacionesException extends RuntimeException {

    private String code;

    public ValidacionesException(String code, String message) {
        super(message);
        this.code = code;
    }
    
}
