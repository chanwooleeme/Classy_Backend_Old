package com.prototype.classyBackEnd.video.delete.service;

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

@Service
@RequiredArgsConstructor
public class VideoDeleteService {

    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void removeVideo(Long memberId, Long videoId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(MemberNotFoundException::new);
        Video video = videoRepository.findById(videoId).orElseThrow(VideoNotFoundException::new);
        if(!video.getMember().equals(member)) {
            throw new AccessDeniedException();
        }
        videoRepository.delete(video);
    }
}
