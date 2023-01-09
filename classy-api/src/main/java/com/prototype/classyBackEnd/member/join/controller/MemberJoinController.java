package com.prototype.classyBackEnd.member.join.controller;

import com.prototype.classyBackEnd.member.domain.TokenAuthentication;
import com.prototype.classyBackEnd.member.join.service.EmailMemberJoinService;
import com.prototype.classyBackEnd.member.join.service.MemberJoinService;
import com.prototype.classyBackEnd.member.join.service.dtos.MemberJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberJoinController {

    private final EmailMemberJoinService emailMemberJoinService;


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello");
    }

    @PostMapping("/v1/member/email")
    public ResponseEntity<String> joinEmailMember(@RequestBody @Valid MemberJoinRequest request) {
        emailMemberJoinService.joinMember(request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/v1/member/send-email")
    public ResponseEntity<String> sendEmail(String email) {
        emailMemberJoinService.sendMail(email);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/v1/member/verify-email")
    public ResponseEntity<String> verifyEmail(String email, Integer authCode) {
        emailMemberJoinService.verify(email, authCode);
        return ResponseEntity.ok("ok");
    }
}
