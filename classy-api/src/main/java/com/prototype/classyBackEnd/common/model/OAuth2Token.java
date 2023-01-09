package com.prototype.classyBackEnd.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2Token {
    private String bearerToken;
    private String bearer;
    private String token;

    public static String makeTokenWithPrefixedTokenType(String token) {
        return token.contains("Bearer ") ? token : "Bearer " + token;
    }

    public static OAuth2Token from(String bearerToken) {
        OAuth2Token token = new OAuth2Token();
        token.bearer = "Bearer";
        token.bearerToken = makeTokenWithPrefixedTokenType(bearerToken);
        token.token = token.bearerToken.substring(7);
        return token;
    }
}
