package com.prototype.classyBackEnd.interceptor;

import com.prototype.classyBackEnd.common.exception.model.ApiException;
import com.prototype.classyBackEnd.common.exception.model.ErrorCode;
import com.prototype.classyBackEnd.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationInterceptor extends MemberAuthenticationInterceptor implements HandlerInterceptor {

    private KakaoMemberTokenAuthenticationInterceptor kakaoMemberTokenAuthenticationInterceptor;
    private EmailMemberTokenAuthenticationInterceptor emailMemberTokenAuthenticationInterceptor;

    public TokenAuthenticationInterceptor(KakaoMemberTokenAuthenticationInterceptor kakaoMemberTokenAuthenticationInterceptor, EmailMemberTokenAuthenticationInterceptor emailMemberTokenAuthenticationInterceptor) {
        this.kakaoMemberTokenAuthenticationInterceptor = kakaoMemberTokenAuthenticationInterceptor;
        this.emailMemberTokenAuthenticationInterceptor = emailMemberTokenAuthenticationInterceptor;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isHandlerMethodExcludeThisInterceptor((HandlerMethod) handler)) {
            return true;
        }

        Member.AuthenticationType authenticationType = Member.AuthenticationType.getCodeOf((request.getParameter("authenticationType")));
        if (authenticationType.isNone()) {
            throw new ApiException(ErrorCode.MEMBER_AUTHENTICATION_TYPE_IS_NULL);
        }

        if (authenticationType.equals(Member.AuthenticationType.KAKAO)) {
            return kakaoMemberTokenAuthenticationInterceptor.preHandle(request, response, handler);
        }

        else if(authenticationType.equals(Member.AuthenticationType.EMAIL)) {
            return emailMemberTokenAuthenticationInterceptor.preHandle(request, response, handler);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
