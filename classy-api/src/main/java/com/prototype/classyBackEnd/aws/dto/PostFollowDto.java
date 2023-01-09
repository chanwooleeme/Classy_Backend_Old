package com.prototype.classyBackEnd.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostFollowDto {
    private Long followerId;
    private Long postId;
}
