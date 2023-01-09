package com.prototype.classyBackEnd.video.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/*
자료형을 넣는건 좋지 않지만
VideoResponse & VideosResponse 조합일 경우 혼동되는 경우가 너무 많음.
 */
@Getter
@RequiredArgsConstructor(staticName = "from")
public class VideoListResponse {
    private final List<VideoResponse> videoResponses;
}
