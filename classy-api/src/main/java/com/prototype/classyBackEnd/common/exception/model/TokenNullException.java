package com.prototype.classyBackEnd.common.exception.model;

public class TokenNullException extends RuntimeException{
    public TokenNullException(String message) {
        super(message);
    }
    public TokenNullException() {
        this("토큰 값이 널입니다.");
    }
}
