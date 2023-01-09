package com.prototype.classyBackEnd.member.auth.service;

import com.prototype.classyBackEnd.common.exception.model.InvalidTokenException;
import com.prototype.classyBackEnd.common.exception.model.MemberNotFoundException;
import com.prototype.classyBackEnd.common.model.JwtAccessToken;
import com.prototype.classyBackEnd.common.model.JwtRefreshToken;
import com.prototype.classyBackEnd.common.model.JwtResponse;
import com.prototype.classyBackEnd.common.model.JwtToken;
import com.prototype.classyBackEnd.common.utils.GeneralJwtUtils;
import com.prototype.classyBackEnd.common.utils.JwtUtils;
import com.prototype.classyBackEnd.member.auth.domain.dtos.RegenerationAllTokenDto;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberAuthService {

    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;

    @Transactional
    public JwtResponse regenerationAllTokenByEmailAndPassword(RegenerationAllTokenDto dto) {
        Member member = memberRepository.findMemberByEmail(dto.getEmail()).orElseThrow(MemberNotFoundException::new);
        if(!member.getPassword().equals(dto.getPassword())) {
            throw new MemberNotFoundException();
        }
        JwtToken jwtToken = GeneralJwtUtils.generateJwtTokenBy(member.getMemberId());
        member.setJwtTokens(jwtToken);
        return JwtResponse.response(jwtToken);
    }

    @Transactional
    public JwtResponse regenerationAccessTokenByMemberId(Long id) {
        Member member = memberRepository.findByMemberId(id).orElseThrow(MemberNotFoundException::new);
        JwtAccessToken jwtAccessToken = GeneralJwtUtils.generateJwtAccessTokenBy(id);
        member.setAccessToken(jwtAccessToken.getToken());
        return JwtResponse.response(member.getAccessToken(), member.getRefreshToken());
    }

    public void validMemberByTokenId(Long tokenId, String token) {
        //토큰이랑 DB 토큰값이랑 비교하는 로직
        Member member = memberRepository.findMemberByAccessTokenOrRefreshToken(token, token).orElseThrow(MemberNotFoundException::new);
        if(!member.getMemberId().equals(tokenId)) {
            throw new MemberNotFoundException();
        }
    }
}
