package com.prototype.classyBackEnd.interceptor;

import com.prototype.classyBackEnd.client.KakaoApiClient;
import com.prototype.classyBackEnd.client.model.KakaoResponse;
import com.prototype.classyBackEnd.common.exception.model.ApiException;
import com.prototype.classyBackEnd.common.exception.model.ErrorCode;
import com.prototype.classyBackEnd.common.model.OAuth2Token;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KakaoMemberTokenAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private KakaoApiClient apiClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        OAuth2Token token = OAuth2Token.from(request.getHeader("authorization"));
        try {
            KakaoResponse.Member kakaoAccount = apiClient.getKakaoAccountBy(token.getBearerToken());
            request.setAttribute("id", kakaoAccount.getId());
        } catch (FeignException e) {
            e.printStackTrace();
            throw new ApiException(ErrorCode.INVALID_TOKEN);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
