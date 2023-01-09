package com.prototype.classyBackEnd.member.follow.service;

import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.follow.domain.Follow;
import com.prototype.classyBackEnd.member.follow.repository.FollowRepository;
import com.prototype.classyBackEnd.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberService memberService;

    public List<Long> findFollowersById(Long id) {
        return followRepository.findFollowsByFollowingId(id).stream().map(Follow::getFollowerId).collect(Collectors.toList());
    }
}
