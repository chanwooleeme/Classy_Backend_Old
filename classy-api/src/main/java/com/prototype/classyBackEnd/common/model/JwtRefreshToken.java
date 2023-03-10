package com.prototype.classyBackEnd.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRefreshToken {
    private String token;
    private Long exp;
}
