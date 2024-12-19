package com.study.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PasswordEncoder {

    // Salt값 생성
    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    public static boolean matches(String rawPassword, String encodedPassword, String salt) {
        return encode(rawPassword, salt).equals(encodedPassword);
    }

    // 비밀번호 + Salt 해싱
    public static String encode(String password, String salt) {
        return DigestUtils.sha256Hex(password + salt);
    }

}
