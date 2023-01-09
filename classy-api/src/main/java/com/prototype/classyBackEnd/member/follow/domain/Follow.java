package com.prototype.classyBackEnd.member.follow.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
TODO

Member Id 반환 -> Member 반환하도록 변환해야함
 */
@Entity
@Getter
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followingId;

    private Long followerId;

    protected Follow() {}

    public static Follow follow(Long followingId, Long followerId) {
        Follow follow = new Follow();
        follow.followingId = followingId;
        follow.followerId = followerId;
        return follow;
    }
}
