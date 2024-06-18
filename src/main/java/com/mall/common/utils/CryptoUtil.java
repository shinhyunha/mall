package com.mall.common.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.DigestException;
import java.security.MessageDigest;
import java.util.Base64;

@Component
@ConfigurationProperties(prefix = "crypto")
public class CryptoUtil {

    @Value("${crypto.password}")
    private String password;

    @Value("${crypto.byte-array}")
    private int byteArray;

    @Value("${crypto.secret-key-algorithm}")
    private String secretKeyAlgorithm;

    @Value("${crypto.transformation}")
    private String transformation;

    @Value("${crypto.digest-algorithm}")
    private String digestAlgorithm;

    @PostConstruct
    public void init() {
        // 초기화 코드 필요시 여기에 작성
    }

    // 암호화 메소드
    public String encryptText(String value) throws Exception {
        Cipher cipherEnc = Cipher.getInstance(transformation);
        SecretKeySpec keySpec = new SecretKeySpec(hashString(password), secretKeyAlgorithm);
        byte[] iv = new byte[byteArray];
        cipherEnc.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] byteEncryptedValue = cipherEnc.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(byteEncryptedValue);
    }

    // 복호화 메소드
    public String decryptText(String encryptedText) throws Exception {
        Cipher cipherDec = Cipher.getInstance(transformation);
        SecretKeySpec keySpec = new SecretKeySpec(hashString(password), secretKeyAlgorithm);
        byte[] iv = new byte[byteArray];
        cipherDec.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] byteDecryptedText = cipherDec.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(byteDecryptedText);
    }

    // 해시 함수
    public byte[] hashString(String msg) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
            md.update(msg.getBytes());
            return md.digest();
        } catch (Exception e) {
            throw new DigestException("couldn't make digest of partial content");
        }
    }

    // 텍스트 다이제스트 함수
    public String digestText(String input) throws Exception {
        byte[] bytes = input.getBytes();
        MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
        byte[] digest = md.digest(bytes);

        // byte 배열을 16진수 문자열로 변환
        StringBuilder result = new StringBuilder();
        for (byte b : digest) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}

