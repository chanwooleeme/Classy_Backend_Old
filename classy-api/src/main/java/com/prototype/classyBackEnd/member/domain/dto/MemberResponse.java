package com.prototype.classyBackEnd.member.domain.dto;

import com.prototype.classyBackEnd.common.model.JwtToken;
import com.prototype.classyBackEnd.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private Long memberId;
    private String email;
    private String name;
    private String nickname;
    private JwtToken token;

    public static MemberResponse of(Member member, JwtToken token) {
        MemberResponse response = from(member);
        response.token = token;
        return response;
    }

    public static MemberResponse from(Member member) {
        MemberResponse response = new MemberResponse();
        response.memberId = member.getMemberId();
        response.email = member.getEmail();
        response.name = member.getName();
        response.nickname = member.getNickname();
        return response;
    }
}
