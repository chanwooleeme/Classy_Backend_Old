package com.prototype.classyBackEnd.common.mail.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class MemoryEmailVerifyRepository implements EmailVerifyRepository {

    private static Map<String, Integer> store = new HashMap<>();

    @Override
    public void save(String email, Integer authCode) {
        store.put(email, authCode);
    }

    @Override
    public boolean valid(String email, Integer authCode) {
        if (Objects.equals(store.get(email), authCode)) {
            store.remove(email);
            return true;
        }
        return false;
    }
}
