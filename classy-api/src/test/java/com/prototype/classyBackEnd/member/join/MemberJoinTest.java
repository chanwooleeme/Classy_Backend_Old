package com.prototype.classyBackEnd.member.join;

import com.prototype.classyBackEnd.member.domain.Gender;
import com.prototype.classyBackEnd.member.domain.dto.MemberResponse;
import com.prototype.classyBackEnd.member.join.service.MemberJoinService;
import com.prototype.classyBackEnd.member.join.service.dtos.MemberJoinRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MemberJoinTest {

    @Autowired
    MemberJoinService emailMemberJoinService;

    @Autowired
    MemberJoinService kakaoMemberJoinService;

    MemberJoinRequest memberDto() {
        return MemberJoinRequest.generalMemberJoinOf("test@mail.com", "1234", "test_name", "test_nickname", Gender.MALE);
    }

    MemberJoinRequest kakaoMemberDto() {
        return MemberJoinRequest.kakaoMemberJoinOf("test@kakao.com", "lee", "lee_nickname", Gender.MALE);
    }

    @Test
    @DisplayName("메일로_회원가입")
    public void signUp() {
        MemberResponse memberResponse = emailMemberJoinService.joinMember(memberDto());
        Assertions.assertThat(memberResponse.getEmail()).isEqualTo("test@mail.com");
    }

    @Test
    @DisplayName("카카오_회원가입")
    public void kakao_signUp() {
        MemberResponse memberResponse = kakaoMemberJoinService.joinMember(kakaoMemberDto());
        Assertions.assertThat(memberResponse.getEmail()).isEqualTo("test@kakao.com");
    }
}
