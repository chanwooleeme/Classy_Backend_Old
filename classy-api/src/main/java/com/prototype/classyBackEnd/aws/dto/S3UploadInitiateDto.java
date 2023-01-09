package com.prototype.classyBackEnd.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class S3UploadInitiateDto {
    // 업로드할 파일의 originFileName
    private String fileName;
}
