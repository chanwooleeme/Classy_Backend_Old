package com.prototype.classyBackEnd.common.exception.model;

public class ExpiredTokenException extends RuntimeException{
    public ExpiredTokenException(String message) {
        super(message);
    }
    public ExpiredTokenException() {
        this("만료된 토큰 입니다.");
    }
}
