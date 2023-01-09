package com.prototype.classyBackEnd.interceptor;

import com.prototype.classyBackEnd.common.exception.model.ExpiredTokenException;
import com.prototype.classyBackEnd.common.exception.model.InvalidTokenException;
import com.prototype.classyBackEnd.common.exception.model.TokenNullException;
import com.prototype.classyBackEnd.common.model.OAuth2Token;
import com.prototype.classyBackEnd.common.utils.JwtUtils;
import com.prototype.classyBackEnd.member.auth.service.MemberAuthService;
import com.prototype.classyBackEnd.member.login.service.EmailMemberLoginService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailMemberTokenAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private MemberAuthService memberAuthService;

    @Autowired
    @Qualifier("generalJwtUtils")
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        OAuth2Token token;
        try {
            token = OAuth2Token.from(request.getHeader("authorization"));
        }
        catch (NullPointerException e) {
            throw new TokenNullException();
        }
        if (token.getBearerToken().isBlank()) {
            throw new TokenNullException();
        }

        try {
            Long userNo = Long.valueOf(jwtUtils.getClaimsBy(token.getToken()).get("userNo").toString());
            memberAuthService.validMemberByTokenId(userNo, token.getToken());
            request.setAttribute("id", userNo);
            return true;
        } catch (ExpiredJwtException e){
            throw new ExpiredTokenException();
        } catch (MalformedJwtException | SignatureException e) {
            throw new InvalidTokenException();
        }
    }
}
