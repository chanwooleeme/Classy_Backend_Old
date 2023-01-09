package com.prototype.classyBackEnd.member.follow.repository;

import com.prototype.classyBackEnd.member.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

    Long countByFollowerId(Long followingId);

    Long countByFollowingId(Long followerId);

    List<Follow> findFollowsByFollowingId(Long id);
}
