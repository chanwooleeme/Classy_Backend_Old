package com.prototype.classyBackEnd.member.auth.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegenerationAllTokenDto {

    private String email;
    private String password;
}
