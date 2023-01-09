package com.prototype.classyBackEnd.member.login.service;

import com.prototype.classyBackEnd.member.domain.AuthenticationType;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {
    private final MemberRepository memberRepository;

    public void loginMember(Long id) {
        Optional<Member> findMember = memberRepository.findMemberByThirdPartyAccountIdAndAuthenticationType(id, AuthenticationType.KAKAO);
    }
}
