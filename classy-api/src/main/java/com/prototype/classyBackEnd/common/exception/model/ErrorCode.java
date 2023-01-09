package com.prototype.classyBackEnd.common.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_AUTHENTICATION_TYPE_IS_NULL("회원 인증 타입이 NULL 입니다.", HttpStatus.BAD_REQUEST.value()),
    INVALID_TOKEN("유효하지 않은 토큰 입니다.", HttpStatus.BAD_REQUEST.value()),
    ALREADY_JOINED_MEMBER("이미 가입한 회원입니다.", HttpStatus.CONFLICT.value()),
    MEMBER_NOT_FOUND("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST.value())
    ;

    private final String message;
    private final int StatusCode;
}
