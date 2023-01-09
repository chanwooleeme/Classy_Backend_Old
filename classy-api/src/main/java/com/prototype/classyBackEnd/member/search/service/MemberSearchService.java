package com.prototype.classyBackEnd.member.search.service;

import com.prototype.classyBackEnd.common.exception.model.AlreadyExistsException;
import com.prototype.classyBackEnd.common.exception.model.ApiException;
import com.prototype.classyBackEnd.common.exception.model.ErrorCode;
import com.prototype.classyBackEnd.common.exception.model.MemberNotFoundException;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSearchService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findByMemberId(id).orElseThrow(MemberNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public void findMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        if (member.isPresent()) {
            throw new AlreadyExistsException("이미 사용중인 메일 입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void findMemberByNickname(String nickname) {
        Optional<Member> member = memberRepository.findMemberByNickname(nickname);
        if (member.isPresent()) {
            throw new AlreadyExistsException("이미 사용중인 닉네임 입니다.");
        }
    }
}
