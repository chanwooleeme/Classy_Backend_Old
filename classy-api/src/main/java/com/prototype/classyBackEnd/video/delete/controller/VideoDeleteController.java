package com.prototype.classyBackEnd.video.delete.controller;

import com.prototype.classyBackEnd.common.swagger.SwaggerJwt;
import com.prototype.classyBackEnd.member.domain.TokenAuthentication;
import com.prototype.classyBackEnd.video.delete.service.VideoDeleteService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "비디오 삭제 API 목록", tags = {"[VIDEO][DELETE]"})
@RestController
@RequiredArgsConstructor
public class VideoDeleteController {

    private final VideoDeleteService videoDeleteService;

    @ApiOperation(value = "비디오 삭제")
    @SwaggerJwt
    @TokenAuthentication
    @DeleteMapping("/v1/video/{videoId}")
    public ResponseEntity<Void> deleteVideo(@ApiParam(hidden = true) @RequestAttribute Long id, @PathVariable Long videoId) {
        videoDeleteService.removeVideo(id, videoId);
        return ResponseEntity.ok().build();
    }
}
