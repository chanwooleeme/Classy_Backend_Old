package com.prototype.classyBackEnd.common.exception.model;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private String message;
    private int statusCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        message = errorCode.getMessage();
        statusCode = errorCode.getStatusCode();
    }

}
