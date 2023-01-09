package com.prototype.classyBackEnd.member.auth.controller;

import com.prototype.classyBackEnd.common.model.JwtResponse;
import com.prototype.classyBackEnd.common.swagger.SwaggerJwt;
import com.prototype.classyBackEnd.member.auth.domain.dtos.RegenerationAllTokenDto;
import com.prototype.classyBackEnd.member.auth.service.MemberAuthService;
import com.prototype.classyBackEnd.member.domain.TokenAuthentication;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberAuthController {

    private final MemberAuthService memberAuthService;

    @PostMapping("/v1/member/all-token")
    public ResponseEntity<JwtResponse> generateTokenByEmailAndPassword(@RequestBody RegenerationAllTokenDto dto) {
        return ResponseEntity.ok(memberAuthService.regenerationAllTokenByEmailAndPassword(dto));
    }

    @SwaggerJwt
    @TokenAuthentication
    @PostMapping("/v1/member/token")
    public ResponseEntity<JwtResponse> generateTokenByRefreshToken(@ApiParam(hidden = true) @RequestAttribute Long id) {
        return ResponseEntity.ok(memberAuthService.regenerationAccessTokenByMemberId(id));
    }

}
