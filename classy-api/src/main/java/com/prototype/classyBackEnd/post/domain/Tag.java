package com.prototype.classyBackEnd.post.domain;

import javax.persistence.*;

@Entity
public class Tag {

    @Id @GeneratedValue
    private Long tagId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public static Tag create(String name, Post post) {
        Tag tag = new Tag();
        tag.name = name;
        tag.post = post;
        return tag;
    }
}
