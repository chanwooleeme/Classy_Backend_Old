package com.prototype.classyBackEnd.member.profile.service;

import com.prototype.classyBackEnd.aws.S3Service;
import com.prototype.classyBackEnd.common.exception.model.NoSuchElementFoundException;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.follow.domain.Follow;
import com.prototype.classyBackEnd.member.profile.domain.dto.ProfileRequestDto;
import com.prototype.classyBackEnd.member.profile.domain.dto.ProfileResponseDto;
import com.prototype.classyBackEnd.member.follow.repository.FollowRepository;
import com.prototype.classyBackEnd.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;
    private final S3Service s3Service;

    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findByMemberId(id).orElseThrow(NoSuchElementFoundException::new);
    }

    public ProfileResponseDto updateProfile(Long id, ProfileRequestDto profileRequestDto) {
        Member member = findMemberById(id);
        if(isPresent(profileRequestDto.getIntroduction()))
            member = updateIntroduction(member, profileRequestDto.getIntroduction());
        if(isPresent(profileRequestDto.getName()))
            member = updateName(member, profileRequestDto.getName());
        if(isPresent(profileRequestDto.getNickname()))
            member = updateNickname(member, profileRequestDto.getNickname());
        if(isPresent(profileRequestDto.getBackgroundImage()))
            member = updateBackgroundImage(member, profileRequestDto.getBackgroundImage());
        if(isPresent(profileRequestDto.getProfileImage()))
            member = updateProfileImage(member, profileRequestDto.getProfileImage());
        return ProfileResponseDto.from(memberRepository.save(member));
    }

    public Boolean isPresent(Object o) {
        return o != null && !o.equals("");
    }

    public Member updateIntroduction(Member member, String introduction) {
        member.setIntroduction(introduction);
        return member;
    }

    public Member updateNickname(Member member, String nickname) {
        member.setNickname(nickname);
        return member;
    }

    @Transactional
    public Member updateName(Member member, String name) {
        member.setName(name);
        return member;
    }

    @Transactional
    public Member updateBackgroundImage(Member member, MultipartFile file) {
        String backgroundImageUrl = s3Service.updateBackgroundImage(member.getMemberId(), file);
        member.setBackgroundImageUrl(backgroundImageUrl);
        return member;
    }

    @Transactional
    public void resetBackgroundImageToDefault(Member member) {
        member.setBackgroundImageUrl("");
    }

    @Transactional
    public Member updateProfileImage(Member member, MultipartFile file) {
        String profileImageUrl = s3Service.updateProfileImage(member.getMemberId(), file);
        member.setProfileImageUrl(profileImageUrl);
        return member;
    }

    @Transactional
    public Member resetProfileImageToDefault(Member member) {
        member.setProfileImageUrl("");
        return member;
    }

    @Transactional
    public void follow(Long followerId, Long followingId) {
        followRepository.save(Follow.follow(followerId, followingId));
        Member follower = findMemberById(followerId);
        Member following = findMemberById(followingId);
        follower.setFollowing(followRepository.countByFollowerId(followingId));
        following.setFollower(followRepository.countByFollowingId(followerId));
        memberRepository.save(follower);
        memberRepository.save(following);
    }

    @Transactional
    public void unfollow(Long followerId, Long followingId) {
        followRepository.deleteByFollowerIdAndFollowingId(followerId,followingId);
        Member follower = findMemberById(followerId);
        Member following = findMemberById(followingId);
        follower.setFollowing(followRepository.countByFollowerId(followingId));
        following.setFollower(followRepository.countByFollowingId(followerId));
        memberRepository.save(follower);
        memberRepository.save(following);
    }

}
