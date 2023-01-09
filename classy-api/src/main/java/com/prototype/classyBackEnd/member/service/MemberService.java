package com.prototype.classyBackEnd.member.service;

import com.prototype.classyBackEnd.common.exception.model.MemberNotFoundException;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByMemberId(Long id) {
        return memberRepository.findByMemberId(id).orElseThrow(MemberNotFoundException::new);
    }
}
