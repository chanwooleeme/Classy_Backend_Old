package com.prototype.classyBackEnd.member.profile.controller;

import com.prototype.classyBackEnd.common.swagger.SwaggerJwt;
import com.prototype.classyBackEnd.member.domain.TokenAuthentication;
import com.prototype.classyBackEnd.member.profile.domain.dto.ProfileRequestDto;
import com.prototype.classyBackEnd.member.profile.domain.dto.ProfileResponseDto;
import com.prototype.classyBackEnd.member.profile.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "프로필 API 목록", tags = {"[PROFILE]"})
@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @ApiOperation(value = "사용자 아이디로 프로필 조회")
    @GetMapping("/v1/member/{id}/profile")
    public ResponseEntity<ProfileResponseDto> findProfileBy(@PathVariable Long id) {
        return ResponseEntity.ok(ProfileResponseDto.from(profileService.findMemberById(id)));
    }

    @ApiOperation(value = "토큰으로 자신의 프로필 조회")
    @TokenAuthentication
    @SwaggerJwt
    @GetMapping("/v1/member/profile")
    public ResponseEntity<ProfileResponseDto> getProfileBy(@ApiParam(hidden=true) @RequestAttribute Long id) {
        return ResponseEntity.ok(ProfileResponseDto.from(profileService.findMemberById(id)));
    }

    @ApiOperation(value = "프로필 업데이트", notes = "업데이트 할 부분만 파라미터나 바디로 넣어서 요청(여러개 가능)")
    @TokenAuthentication
    @SwaggerJwt
    @PatchMapping(value = "/v1/member/profile")
    public ResponseEntity<ProfileResponseDto> updateProfile(@ApiParam(hidden = true) @RequestAttribute Long id,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String nickname,
                                                            @RequestParam(required = false) String introduction,
                                                            @RequestPart(required = false) MultipartFile backgroundImage,
                                                            @RequestPart(required = false) MultipartFile profileImage) {
        return ResponseEntity.ok(profileService.updateProfile(id, ProfileRequestDto.of(introduction, name, nickname, backgroundImage, profileImage)));
    }

    @TokenAuthentication
    @SwaggerJwt
    @PostMapping("/v1/member/{followerId}/follow")
    public ResponseEntity<?> follow(@ApiParam(hidden=true) @RequestAttribute Long id, @PathVariable Long followerId) {
        profileService.follow(id, followerId);
        return ResponseEntity.ok().build();
    }

    @TokenAuthentication
    @SwaggerJwt
    @PostMapping("/v1/member/{followerId}/unfollow")
    public ResponseEntity<?> unfollow(@ApiParam(hidden=true) @RequestAttribute Long id, @PathVariable Long followerId) {
        profileService.unfollow(id, followerId);
        return ResponseEntity.ok().build();
    }
}
