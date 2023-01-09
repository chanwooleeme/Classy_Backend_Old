package com.prototype.classyBackEnd.common.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

@ApiImplicitParams({
        @ApiImplicitParam(value = "Bearer Token", name = "Authorization", required = true, paramType = "header", defaultValue = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjbGFzc3ktYWNjZXNzIiwidXNlck5vIjoyLCJpZCI6IjBhZWEzM2ZlLWU2ZjYtNGJlOC05ZmU5LWQzNjMzZDYxNGUwNiIsImV4cCI6MjQzODUwMjIwNywiaWF0IjoxNjYwOTAyMjA3fQ.5f43ZI8rPml4uGtfACnxlsGFAZA4VT6pL5u5Oxkh-Ns",dataTypeClass = String.class),
        @ApiImplicitParam(value = "KAKAO, APPLE, EMAIL", name = "authenticationType", required = true, paramType = "query", defaultValue = "EMAIL", dataTypeClass = String.class)
})
public @interface SwaggerJwt {
}
