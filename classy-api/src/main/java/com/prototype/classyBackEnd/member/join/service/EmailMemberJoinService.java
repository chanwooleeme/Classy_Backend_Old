package com.prototype.classyBackEnd.member.join.service;

import com.prototype.classyBackEnd.common.exception.model.AlreadyExistsException;
import com.prototype.classyBackEnd.common.exception.model.EmailAuthCodeNotMatchException;
import com.prototype.classyBackEnd.common.mail.MailAuthentication;
import com.prototype.classyBackEnd.common.mail.repository.EmailVerifyRepository;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.domain.dto.MemberResponse;
import com.prototype.classyBackEnd.member.join.service.dtos.MemberJoinRequest;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailMemberJoinService implements MemberJoinService {

    private final MemberRepository memberRepository;
    private final MailAuthentication authentication;
    private final EmailVerifyRepository emailVerifyRepository;

    @Override
    @Transactional
    public MemberResponse joinMember(MemberJoinRequest request) {
        request.setAuthenticationType(Member.AuthenticationType.EMAIL);
        if (memberRepository.findMemberByEmail(request.getEmail()).isEmpty()) {
            Member member = Member.from(request);
            memberRepository.save(member);
            return MemberResponse.from(member);
        } else throw new AlreadyExistsException("이미 등록된 이메일 입니다.");
    }

    public void sendMail(String email) {
        authentication.join(email);
    }

    public void verify(String email, Integer authCode) {
        if (!emailVerifyRepository.valid(email, authCode)) {
            throw new EmailAuthCodeNotMatchException();
        }
    }
}
