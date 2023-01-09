package com.prototype.classyBackEnd.member.login.domain.dto;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
    private String email;
    private String password;

    public static MemberLoginRequest of(String email, String password) {
        MemberLoginRequest loginDto = new MemberLoginRequest();
        loginDto.email = email;
        loginDto.password = password;
        return loginDto;
    }
}
