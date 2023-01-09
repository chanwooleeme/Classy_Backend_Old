package com.prototype.classyBackEnd.common.exception.model;

public class EmailAuthCodeNotMatchException extends RuntimeException{
    public EmailAuthCodeNotMatchException(String message) {
        super(message);
    }
    public EmailAuthCodeNotMatchException(){
        super("인증번호가 일치하지 않습니다.");
    }
}
