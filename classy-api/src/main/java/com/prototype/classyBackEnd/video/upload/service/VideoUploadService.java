package com.prototype.classyBackEnd.video.upload.service;

import com.prototype.classyBackEnd.aws.S3Service;
import com.prototype.classyBackEnd.common.exception.model.AccessDeniedException;
import com.prototype.classyBackEnd.common.exception.model.MemberNotFoundException;
import com.prototype.classyBackEnd.common.exception.model.VideoNotFoundException;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import com.prototype.classyBackEnd.video.domain.Video;
import com.prototype.classyBackEnd.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoUploadService {

    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    @Transactional
    public void save(Video video) {
        videoRepository.save(video);
    }

    @Transactional
    public void uploadVideo(Long memberId, String title, MultipartFile file) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(MemberNotFoundException::new);
        String s3Title = s3Service.uploadVideo(file);
        Video video = Video.of(title, s3Title, s3Service.s3TitleToCdnURL(s3Title), s3Service.s3TitleToDefaultThumbnailURL(s3Title), member);
        videoRepository.save(video);
    }

    @Transactional
    public void uploadVideo(Long memberId, String title, Long videoDuration, MultipartFile file) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(MemberNotFoundException::new);
        String s3Title = s3Service.uploadVideo(file);
        Optional<Video> findVideo = findVideoByS3Title(s3Title);
        Video video;
        if (findVideo.isPresent()) {
            video = findVideo.get();
            video.updateLastUpdateTime();
        }
        else video = Video.of(title, s3Title, s3Service.s3TitleToCdnURL(s3Title), s3Service.s3TitleToDefaultThumbnailURL(s3Title), videoDuration,member);
        videoRepository.save(video);
    }

    @Transactional
    public void updateThumbnail(Long memberId, Long videoId, MultipartFile file) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(MemberNotFoundException::new);
        Video video = videoRepository.findById(videoId).orElseThrow(VideoNotFoundException::new);
        if (!member.getMemberId().equals(video.getMember().getMemberId())) {
            throw new AccessDeniedException();
        }
        String thumbnailUrl = s3Service.updateThumbnail(video.getS3Title(), file);
        video.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(video);
    }

    private Optional<Video> findVideoByS3Title(String s3Title) {
        return videoRepository.findVideoByS3Title(s3Title);
    }
}
