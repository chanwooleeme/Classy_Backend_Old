package com.prototype.classyBackEnd.post.domain.dto;

import com.prototype.classyBackEnd.post.domain.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class PostUploadRequest {
    private List<MultipartFile> images;
    private String caption;
    private List<String> tags;


}
