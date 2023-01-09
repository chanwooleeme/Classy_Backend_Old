package com.prototype.classyBackEnd.common.exception.model;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message) {
        super(message);
    }
    public InvalidTokenException() {
        this("유효하지 않은 토큰 입니다.");
    }
}
