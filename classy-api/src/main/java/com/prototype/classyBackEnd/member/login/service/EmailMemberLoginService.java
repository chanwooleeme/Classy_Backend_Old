package com.prototype.classyBackEnd.member.login.service;

import com.prototype.classyBackEnd.common.exception.model.MemberNotFoundException;
import com.prototype.classyBackEnd.common.model.JwtToken;
import com.prototype.classyBackEnd.common.utils.GeneralJwtUtils;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.domain.dto.MemberResponse;
import com.prototype.classyBackEnd.member.login.domain.dto.MemberLoginRequest;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailMemberLoginService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse login(MemberLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Member member = memberRepository.findMemberByEmailAndPassword(email, password).orElseThrow(MemberNotFoundException::new);
        JwtToken jwtToken = GeneralJwtUtils.generateJwtTokenBy(member.getMemberId());
        member.setJwtTokens(jwtToken);

        return MemberResponse.of(member, jwtToken);
    }

}
