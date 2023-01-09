package com.prototype.classyBackEnd.video.search.service;

import com.prototype.classyBackEnd.common.exception.model.MemberNotFoundException;
import com.prototype.classyBackEnd.common.exception.model.VideoNotFoundException;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import com.prototype.classyBackEnd.video.domain.Video;
import com.prototype.classyBackEnd.video.domain.dto.VideoListResponse;
import com.prototype.classyBackEnd.video.domain.dto.VideoResponse;
import com.prototype.classyBackEnd.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoSearchService {

    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public VideoListResponse getVideosBy(String nickname, Pageable pageable) {
        Member member = memberRepository.findMemberByNickname(nickname).orElseThrow(MemberNotFoundException::new);
        return VideoListResponse.from(videoRepository.findVideosByMember(member, pageable)
                .stream()
                .map(VideoResponse::from)
                .collect(Collectors.toList()));
    }

    @Transactional()
    public VideoResponse getVideoBy(Long videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(VideoNotFoundException::new);
        video.increaseViews();
        return VideoResponse.from(videoRepository.findById(videoId).orElseThrow(VideoNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public VideoListResponse getAllVideos(Pageable pageable) {
        return VideoListResponse.from(videoRepository.findAllBy(pageable).stream()
                .map(VideoResponse::from)
                .collect(Collectors.toList())
        );
    }
}
