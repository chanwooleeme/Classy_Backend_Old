package com.prototype.classyBackEnd.post.controller;

import com.prototype.classyBackEnd.common.swagger.SwaggerJwt;
import com.prototype.classyBackEnd.member.domain.TokenAuthentication;
import com.prototype.classyBackEnd.post.domain.dto.PostUploadRequest;
import com.prototype.classyBackEnd.post.service.PostService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @TokenAuthentication
    @SwaggerJwt
    @PostMapping("/v1/me/post")
    public ResponseEntity<String> post(@ApiParam(hidden = true) @RequestAttribute Long id, PostUploadRequest postUploadRequest) {
        postService.save(id, postUploadRequest);
        return ResponseEntity.ok("ok");
    }

}
