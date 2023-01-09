package com.prototype.classyBackEnd.member.profile.domain.dto;

import com.prototype.classyBackEnd.member.domain.Member;
import lombok.Getter;

@Getter
public class ProfileResponseDto {

    private String name;
    private String nickname;
    private String introduction;
    private String backgroundImageUrl;
    private String profileImageUrl;
    private Long following;
    private Long follower;

    protected ProfileResponseDto() {}

    public static ProfileResponseDto from(Member member) {
        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        profileResponseDto.name = member.getName();
        profileResponseDto.nickname = member.getNickname();
        profileResponseDto.introduction = member.getIntroduction();
        profileResponseDto.backgroundImageUrl = member.getBackgroundImageUrl();
        profileResponseDto.profileImageUrl = member.getProfileImageUrl();
        profileResponseDto.following = member.getFollowing();
        profileResponseDto.follower = member.getFollower();
        return profileResponseDto;
    }
}
