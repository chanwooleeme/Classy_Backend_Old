package com.prototype.classyBackEnd.video.domain.dto;

import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.video.domain.Video;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VideoResponse {

    private Long videoId;
    private String title;
    private String nickname;
    private String videoUrl;
    private String thumbnailUrl;
    private String profileUrl;
    private Long videoDuration;
    private LocalDateTime uploadDateTime; //업로드 시간
    private LocalDateTime lastModifiedTime;
    private Long views;

    public static VideoResponse from(Video video) {
        VideoResponse videoResponse = new VideoResponse();
        Member member = video.getMember();

        videoResponse.videoId = video.getVideoId();
        videoResponse.nickname = member.getNickname();
        videoResponse.title = video.getTitle();
        videoResponse.videoUrl = video.getVideoUrl();
        videoResponse.thumbnailUrl = video.getThumbnailUrl();
        videoResponse.uploadDateTime = video.getUploadDateTime();
        videoResponse.lastModifiedTime = video.getLastModifiedTime();
        videoResponse.views = video.getViews();
        videoResponse.videoDuration = video.getVideoDuration();
        videoResponse.profileUrl = video.getMember().getProfileImageUrl();
        return videoResponse;
    }
}
