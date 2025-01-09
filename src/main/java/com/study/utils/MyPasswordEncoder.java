package com.study.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MyPasswordEncoder {

    // Salt값 생성
    public String generateSalt() {
        return UUID.randomUUID().toString();
    }

    public boolean matches(String rawPassword, String encodedPassword, String salt) {
        return encode(rawPassword, salt).equals(encodedPassword);
    }

    // 비밀번호 + Salt 해싱
    public String encode(String password, String salt) {
        return DigestUtils.sha256Hex(password + salt);
    }

}
