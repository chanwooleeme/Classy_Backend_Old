package com.prototype.classyBackEnd.post.domain;

import com.prototype.classyBackEnd.member.domain.Member;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Column
    @Id @GeneratedValue
    @Getter
    private Long postId;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Image> images = new ArrayList<Image>();

    private String caption;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime uploadDateTime;

    public void from(List<Image> images, String caption, List<Tag> tags, Member member) {
        this.images = images;
        this.caption = caption;
        this.tags = tags;
        this.member = member;
        this.uploadDateTime = LocalDateTime.now();
    }
}
