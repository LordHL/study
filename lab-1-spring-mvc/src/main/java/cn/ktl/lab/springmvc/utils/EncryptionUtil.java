package cn.ktl.lab.springmvc.utils;


import cn.ktl.lab.springmvc.constant.MagicNumConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * @Author lin ho
 * Des: 通用加密util
 */
@Slf4j
@Component
public class EncryptionUtil {

    private static final String DEFAULT_CYPHER = "AES/CBC/PKCS5Padding";
    private static final String DEFAULT_ALGORITHM = "AES";

    @Value("${oneforma.encryption.key:a8f5f167f44f4964e6c998dee827110c}")
    private String key;

    @Value("${oneforma.encryption.vector:d4e5f6g7h8i9j0k1}")
    private String vector;

    public String encrypt(String value) {
        validateKeyAndVector();
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value to encrypt cannot be null or empty");
        }
        try {
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            log.error("Encryption failed", e);
//            throw EncryptionException.of(ENCRYPTION_FAILED);
            throw new RuntimeException();
        }
    }

    public String decrypt(String encrypted) {
        validateKeyAndVector();
        if (encrypted == null || encrypted.isEmpty()) {
            throw new IllegalArgumentException("Value to decrypt cannot be null or empty");
        }
        try {
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("Decryption failed", e);
            throw new RuntimeException();
        }
    }

    private Cipher initCipher(int mode) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), DEFAULT_ALGORITHM);
        Cipher cipher = Cipher.getInstance(DEFAULT_CYPHER);
        cipher.init(mode, secretKey, iv);
        return cipher;
    }

    private void validateKeyAndVector() {
        if (key == null || (key.length() != MagicNumConstant.SIXTEEN && key.length() != MagicNumConstant.TWENTY_FOUR && key.length() != MagicNumConstant.THIRTY_TWO)) {
            throw new RuntimeException();

        }
        if (vector == null || vector.length() != MagicNumConstant.SIXTEEN) {
            throw new RuntimeException();
        }
    }

}

