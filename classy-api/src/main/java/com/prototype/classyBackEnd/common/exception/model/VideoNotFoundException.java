package com.prototype.classyBackEnd.common.exception.model;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(String message) {
        super(message);
    }
    public VideoNotFoundException() {
        this("찾을 수 없는 비디오 입니다.");
    }
}
