package com.prototype.classyBackEnd.member.join.service.dtos;

import com.prototype.classyBackEnd.member.domain.Gender;
import com.prototype.classyBackEnd.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberJoinRequest {

    private String email;

    private String password;


    //이름
    private String name;

    //닉네임(사용자 이름)
    private String nickname;

    private Gender gender;

    @Setter
    private Member.AuthenticationType authenticationType;

    //이메일 회원
    public static MemberJoinRequest generalMemberJoinOf(String email, String password, String name, String nickname, Gender gender) {
        MemberJoinRequest join = new MemberJoinRequest();
        join.email = email;
        join.password = password;
        join.name = name;
        join.nickname = nickname;
        join.gender = gender;
        return join;
    }

    //Third-party 회원
    public static MemberJoinRequest kakaoMemberJoinOf(String email, String name, String nickname, Gender gender) {
        MemberJoinRequest join = new MemberJoinRequest();
        join.email = email;
        join.name = name;
        join.nickname = nickname;
        join.gender = gender;
        return join;
    }

    public static MemberJoinRequest kakaoMemberJoinOf(String email, Gender gender) {
        MemberJoinRequest join = new MemberJoinRequest();
        join.email = email;
        join.gender = gender;
        return join;
    }
}
