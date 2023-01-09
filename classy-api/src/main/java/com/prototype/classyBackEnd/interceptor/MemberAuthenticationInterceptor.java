package com.prototype.classyBackEnd.interceptor;

import com.prototype.classyBackEnd.member.domain.TokenAuthentication;
import org.springframework.web.method.HandlerMethod;

public abstract class MemberAuthenticationInterceptor {
    protected boolean isHandlerMethodExcludeThisInterceptor(HandlerMethod handlerMethod) {
        TokenAuthentication tokenAuthentication = handlerMethod.getMethodAnnotation(TokenAuthentication.class);
        return tokenAuthentication == null;
    }
}
