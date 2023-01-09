package com.prototype.classyBackEnd.member.search.controller;

import com.prototype.classyBackEnd.member.search.service.MemberSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member/search")
@RequiredArgsConstructor
public class MemberSearchController {

    private final MemberSearchService memberSearchService;

    @GetMapping("/email")
    public ResponseEntity<String> checkEmail(String email) {
        memberSearchService.findMemberByEmail(email);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/nickname")
    public ResponseEntity<String> checkNickname(String nickname) {
        memberSearchService.findMemberByNickname(nickname);
        return ResponseEntity.ok("ok");
    }

}
