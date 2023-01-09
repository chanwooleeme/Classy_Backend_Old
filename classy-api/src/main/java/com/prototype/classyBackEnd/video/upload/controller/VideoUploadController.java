package com.prototype.classyBackEnd.video.upload.controller;

import com.prototype.classyBackEnd.common.swagger.SwaggerJwt;
import com.prototype.classyBackEnd.member.domain.TokenAuthentication;
import com.prototype.classyBackEnd.video.upload.service.VideoUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "비디오 업로드 API 목록", tags = {"[VIDEO][UPLOAD]"})
@Slf4j
@RestController
@RequiredArgsConstructor
public class VideoUploadController {

    private final VideoUploadService videoUploadService;

    @ApiOperation(value = "비디오 업로드")
    @SwaggerJwt
    @TokenAuthentication
    @PostMapping(value = "/v1/video")
    public ResponseEntity<String> uploadVideo(@ApiParam(hidden = true) @RequestAttribute Long id, @RequestParam String title, @RequestParam Long videoDuration, @RequestBody MultipartFile file) {
        videoUploadService.uploadVideo(id, title, videoDuration, file);
        return ResponseEntity.ok("OK");
    }

    @ApiOperation("비디오 썸네일 업로드")
    @SwaggerJwt
    @TokenAuthentication
    @PostMapping(value = "/v1/video/{videoId}/thumbnail")
    public ResponseEntity<String> uploadThumbnail(@ApiParam(hidden = true) @RequestAttribute Long id, @PathVariable Long videoId, @RequestBody MultipartFile file) {
        videoUploadService.updateThumbnail(id, videoId, file);
        return ResponseEntity.ok("OK");
    }

}
