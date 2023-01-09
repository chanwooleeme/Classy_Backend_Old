package com.prototype.classyBackEnd.post.domain;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long imageId;

    private String path;

    @ManyToOne
    private Post post;

    public static Image create(String path, Post post) {
        Image image = new Image();
        image.path = path;
        image.post = post;
        return image;
    }

}
