package com.prototype.classyBackEnd.aws;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
class S3ServiceTest {

    @Autowired
    private S3Service s3Service;

    @Test
    public void getMockExcelUploadTest() throws IOException {
        /*MockMultipartHttpServletRequest multipartHttpServletRequest = new MockMultipartHttpServletRequest();*/ // controller test 시 사용
        String fileName = "test";
        String contentType = "mp4";
        String filePath = "/Users/lee/Desktop/video/video.mp4";
        MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, filePath);

        log.debug(s3Service.updateBackgroundImage(1L, mockMultipartFile));

    }

    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
}