package com.prototype.classyBackEnd.common.exception.model;

public class NoSuchElementFoundException extends RuntimeException{
    public NoSuchElementFoundException(String message) {
        super(message);
    }
    public NoSuchElementFoundException() {
        this("대상을 찾을 수 없습니다.");
    }
}
