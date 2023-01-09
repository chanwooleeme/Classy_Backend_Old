package com.prototype.classyBackEnd.aws;

import com.prototype.classyBackEnd.common.utils.SHA256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String originBucket;  // S3 버킷 이름

    @Value("${cloud.aws.s3.cdnBucket}")
    private String cdnBucket;

    @Value("${cloud.aws.cdnUrl}")
    private String cdnUrl;

    @Value("${cloud.aws.s3.imageBucket}")
    private String imageBucket;

    public String upload(MultipartFile file, String bucket, String path) {
        String fileName = getEncryptFileName(file);
        log.info(fileName);
        putS3(file, bucket, path + fileName);
        return fileName;
    }

    private String getEncryptFileName(MultipartFile file){
        String fileName = "";
        try {
            fileName = SHA256.encrypt(file.getSize() + file.getContentType() + file.getName());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private String putS3(MultipartFile file, String bucket, String key) {
        try {
            s3Client.putObject(getPutObjectRequest(bucket, key), getFileRequestBody(file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private RequestBody getFileRequestBody(MultipartFile file) throws IOException {
        return RequestBody.fromInputStream(file.getInputStream(), file.getSize());
    }

    private PutObjectRequest getPutObjectRequest(String bucket, String key) {
        return PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();
    }

    //미디어파일을 s3버킷에 저장
    public String uploadVideo(MultipartFile file) {
        return upload(file, originBucket, "");
    }
    public String uploadImage(MultipartFile file) {
        return upload(file, imageBucket, "");
    }

    public String updateThumbnail(String key, MultipartFile file) {
        return upload(file, cdnBucket, getThumbnailPath(key));
    }

    public String updateBackgroundImage(Long id, MultipartFile file) {
        return cdnUrl + upload(file, cdnBucket, getBackgroundImagePath(id));
    }

    public String updateProfileImage(Long id, MultipartFile file) {
        return cdnUrl + upload(file, cdnBucket, getProfileImagePath(id));
    }

    public String s3TitleToCdnURL(String s3Title) {
        return cdnUrl + s3Title + "/Default/HLS/" + s3Title + ".m3u8";
    }

    public String s3TitleToDefaultThumbnailURL(String s3Title) {
        return cdnUrl + s3Title + "/Default/Thumbnails/" + s3Title + ".0000000.jpg";
    }

    private String getBackgroundImagePath(Long id) {
        return "Profile/" + id.toString() + "/Background/Image/";
    }

    private String getProfileImagePath(Long id) {
        return "Profile/" + id.toString() + "/Image/";
    }

    private String getThumbnailPath(String key) {
        return key + "/Default/Thumbnails/";
    }
}
