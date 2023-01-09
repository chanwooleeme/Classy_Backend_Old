package com.prototype.classyBackEnd.member.repository;

import com.prototype.classyBackEnd.member.domain.AuthenticationType;
import com.prototype.classyBackEnd.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(Long id);

    Optional<Member> findMemberByEmail(String email);

    Optional<Member> findMemberByNickname(String nickname);

    Optional<Member> findMemberByThirdPartyAccountIdAndAuthenticationType(Long thirdPartyAccountId, AuthenticationType authenticationType);

    Optional<Member> findMemberByEmailAndPassword(String email, String password);

    Optional<Member> findMemberByAccessTokenOrRefreshToken(String accessToken, String refreshToken);
}
