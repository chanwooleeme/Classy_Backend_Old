package com.prototype.classyBackEnd.common.config;

import com.prototype.classyBackEnd.interceptor.EmailMemberTokenAuthenticationInterceptor;
import com.prototype.classyBackEnd.interceptor.KakaoMemberTokenAuthenticationInterceptor;
import com.prototype.classyBackEnd.interceptor.TokenAuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final static List<String> TOKEN_KAKAO_USER_AUTHENTICATION_INTERCEPTOR_INCLUDE_URIS =
            Arrays.asList("/v1/kakao/member");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenAuthenticationInterceptor())
                        .addPathPatterns("/v1/**");
        registry.addInterceptor(kakaoMemberTokenAuthenticationInterceptor())
                .addPathPatterns(TOKEN_KAKAO_USER_AUTHENTICATION_INTERCEPTOR_INCLUDE_URIS);
    }

    @Bean
    public TokenAuthenticationInterceptor tokenAuthenticationInterceptor() {
        return new TokenAuthenticationInterceptor(kakaoMemberTokenAuthenticationInterceptor(), emailMemberTokenAuthenticationInterceptor());
    }

    @Bean
    public EmailMemberTokenAuthenticationInterceptor emailMemberTokenAuthenticationInterceptor() {
        return new EmailMemberTokenAuthenticationInterceptor();
    }

    @Bean
    public KakaoMemberTokenAuthenticationInterceptor kakaoMemberTokenAuthenticationInterceptor() {
        return new KakaoMemberTokenAuthenticationInterceptor();
    }
}

