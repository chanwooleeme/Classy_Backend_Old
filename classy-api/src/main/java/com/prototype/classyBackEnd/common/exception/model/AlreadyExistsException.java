package com.prototype.classyBackEnd.common.exception.model;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
