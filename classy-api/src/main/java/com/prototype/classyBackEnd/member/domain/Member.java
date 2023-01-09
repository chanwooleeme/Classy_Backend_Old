package com.prototype.classyBackEnd.member.domain;

import com.prototype.classyBackEnd.common.model.JwtToken;
import com.prototype.classyBackEnd.member.join.service.dtos.MemberJoinRequest;
import com.prototype.classyBackEnd.post.domain.Post;
import com.prototype.classyBackEnd.video.domain.Video;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
//Setter는 테스트시에만
@Setter
@Entity(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "third_party_account")
    private Long thirdPartyAccountId;

    @Column(name = "email")
    private String email;

    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Member.AuthenticationType authenticationType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    private String accessToken;

    private String refreshToken;

    private Long accessTokenExp;

    private Long refreshTokenExp;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    private String backgroundImageUrl;

    private String profileImageUrl;

    private Long following;

    private Long follower;

    private String introduction;

    protected Member() {

    }

    public void setJwtTokens(JwtToken token) {
        this.accessToken = token.getAccessToken().getToken();
        this.accessTokenExp = token.getAccessToken().getExp();
        this.refreshToken = token.getRefreshToken().getToken();
        this.refreshTokenExp = token.getRefreshToken().getExp();
    }

    public static Member from(MemberJoinRequest joinRequest) {
        Member member = new Member();

        member.email = joinRequest.getEmail();
        member.password = joinRequest.getPassword();
        member.name = joinRequest.getName();
        member.nickname = joinRequest.getNickname();
        member.gender = joinRequest.getGender();
        member.authenticationType = joinRequest.getAuthenticationType();
        member.following = 0L;
        member.follower = 0L;
        return member;
    }

    public enum AuthenticationType {
        EMAIL, KAKAO, NONE;

        public static AuthenticationType getCodeOf(String name) {
            for (AuthenticationType type: AuthenticationType.values()) {
                if (type.name().equals(name)) {
                    return type;
                }
            }
            return NONE;
        }

        public boolean isNone() {
            return this.equals(NONE);
        }
    }

}
