package com.prototype.classyBackEnd.member.login.controller;

import com.prototype.classyBackEnd.member.domain.dto.MemberResponse;
import com.prototype.classyBackEnd.member.login.domain.dto.MemberLoginRequest;
import com.prototype.classyBackEnd.member.login.service.EmailMemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberLoginController {

    private final EmailMemberLoginService emailMemberLoginService;

    @PostMapping("/v1/member/auth")
    public ResponseEntity<MemberResponse> emailMemberLogin(@RequestBody MemberLoginRequest request) {
        return ResponseEntity.ok(emailMemberLoginService.login(request));
    }
}
