package com.prototype.classyBackEnd.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String accessToken;
    private String refreshToken;

    public static JwtResponse response(JwtToken token) {
        JwtResponse response = new JwtResponse();
        response.accessToken = token.getAccessToken().getToken();
        response.refreshToken = token.getRefreshToken().getToken();
        return response;
    }

    public static JwtResponse response(String accessToken, String refreshToken) {
        JwtResponse response = new JwtResponse();
        response.accessToken = accessToken;
        response.refreshToken = refreshToken;
        return response;
    }

}
