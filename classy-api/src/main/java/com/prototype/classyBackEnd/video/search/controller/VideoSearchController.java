package com.prototype.classyBackEnd.video.search.controller;

import com.prototype.classyBackEnd.video.domain.dto.VideoListResponse;
import com.prototype.classyBackEnd.video.domain.dto.VideoResponse;
import com.prototype.classyBackEnd.video.search.service.VideoSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "비디오 조회 API 목록", tags = {"[VIDEO][SEARCH]"})
@RequiredArgsConstructor
@RestController
public class VideoSearchController {

    private final VideoSearchService videoSearchService;

    @ApiOperation(value = "닉네임으로 조회")
    @GetMapping(value = "/v1/videos")
    public ResponseEntity<VideoListResponse> getVideos(@RequestParam String nickname, Pageable pageable) {
        return ResponseEntity.ok(videoSearchService.getVideosBy(nickname, pageable));
    }

    @ApiOperation(value = "모든 비디오 조회")
    @GetMapping("/v1/all-videos")
    public ResponseEntity<VideoListResponse> getVideosBy(Pageable pageable) {
        return ResponseEntity.ok(videoSearchService.getAllVideos(pageable));
    }

    @ApiOperation(value = "비디오 아이디로 조회")
    @GetMapping("/v1/video/{videoId}")
    public ResponseEntity<VideoResponse> getVideoBy(@PathVariable Long videoId) {
        return ResponseEntity.ok(videoSearchService.getVideoBy(videoId));
    }
}
