package com.prototype.classyBackEnd.member.profile.domain.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProfileRequestDto {

    private String introduction;

    @ApiParam(value = "이름(중복 가능)")
    private String name;

    @ApiParam(value = "닉네임(중복 불가능)")
    private String nickname;

    private MultipartFile backgroundImage;

    private MultipartFile profileImage;

    public static ProfileRequestDto of(String introduction, String name, String nickname, MultipartFile backgroundImageUrl, MultipartFile profileImageUrl) {
        ProfileRequestDto dto = new ProfileRequestDto();
        dto.introduction = introduction;
        dto.name = name;
        dto.nickname = nickname;
        dto.backgroundImage = backgroundImageUrl;
        dto.profileImage = profileImageUrl;
        return dto;
    }
}
