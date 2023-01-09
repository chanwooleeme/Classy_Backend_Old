package com.prototype.classyBackEnd.video.repository;

import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.video.domain.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findVideosByMember(Member member, Pageable pageable);

    List<Video> findAllBy(Pageable pageable);

    Optional<Video> findVideoByS3Title(String s3Title);
}
