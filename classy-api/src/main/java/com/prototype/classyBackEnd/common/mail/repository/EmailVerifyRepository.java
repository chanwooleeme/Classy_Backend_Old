package com.prototype.classyBackEnd.common.mail.repository;

public interface EmailVerifyRepository {
    void save(String email, Integer authCode);
    boolean valid(String email, Integer authCode);
}
