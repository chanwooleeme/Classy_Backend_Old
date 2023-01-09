package com.prototype.classyBackEnd.common.exception.model;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {
        super(message);
    }
    public AccessDeniedException() {
        this("접근 권한이 없습니다.");
    }
}
