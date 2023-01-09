package com.prototype.classyBackEnd.common.utils;

import io.jsonwebtoken.Claims;

public interface JwtUtils {
    Claims getClaimsBy(String token);
}
