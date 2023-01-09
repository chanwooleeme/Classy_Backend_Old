package com.prototype.classyBackEnd.common.exception.model;

import feign.FeignException;

public class UnauthorizedTokenException extends RuntimeException {
    public UnauthorizedTokenException(String message) {
        super(message);
    }

    public UnauthorizedTokenException() {
        this("만료되었거나 유효하지 않은 토큰입니다.");
    }
}
