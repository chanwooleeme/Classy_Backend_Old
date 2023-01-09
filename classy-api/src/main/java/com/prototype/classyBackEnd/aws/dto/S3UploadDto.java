package com.prototype.classyBackEnd.aws.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class S3UploadDto {

    private String uploadId;
    private String fileName;

    public static S3UploadDto from(String uploadId, String fileName) {
        S3UploadDto response = new S3UploadDto();
        response.uploadId = uploadId;
        response.fileName = fileName;
        return response;
    }
}
