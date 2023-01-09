package com.prototype.classyBackEnd.post.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.classyBackEnd.aws.S3Service;
import com.prototype.classyBackEnd.aws.SQSService;
import com.prototype.classyBackEnd.aws.dto.PostFollowDto;
import com.prototype.classyBackEnd.member.domain.Member;
import com.prototype.classyBackEnd.member.follow.service.FollowService;
import com.prototype.classyBackEnd.member.service.MemberService;
import com.prototype.classyBackEnd.post.domain.Image;
import com.prototype.classyBackEnd.post.domain.Post;
import com.prototype.classyBackEnd.post.domain.Tag;
import com.prototype.classyBackEnd.post.domain.dto.PostUploadRequest;
import com.prototype.classyBackEnd.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final S3Service s3Service;
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final FollowService followService;
    private final SQSService sqsService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Transactional
    public void save(Long id, PostUploadRequest request) {
        Post post = new Post();
        List<Image> images = uploadToS3(request.getImages(), post);
        post.from(images, request.getCaption(), toTags(request.getTags(), post), memberService.findByMemberId(id));
        postRepository.save(post);
        sendMessageToSQS(id, post.getPostId());
    }

    /*
    TODO
    이미지 없을경우
     */
    private List<Image> uploadToS3(List<MultipartFile> files, Post post) {
        return files.stream().map(img -> Image.create(s3Service.uploadImage(img), post)).collect(Collectors.toList());
    }

    private List<Tag> toTags(List<String> tags, Post post) {
        return tags.stream().map(name -> Tag.create(name, post)).collect(Collectors.toList());
    }

    private void sendMessageToSQS(Long id, Long postId) {
        List<Long> followers = followService.findFollowersById(id);
        followers.stream()
                .map(x -> new PostFollowDto(x, postId))
                .map(this::serializePostFollowDto)
                .collect(Collectors.toList())
                .forEach(sqsService::sendMessage);
    }
    private String serializePostFollowDto(PostFollowDto dto) {
        String serialiaze = "";
        try{
            serialiaze = objectMapper.writeValueAsString(dto);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serialiaze;
    }
}
