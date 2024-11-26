package cn.ktl.lab.springmvc.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @Author lin ho
 * Des: 加密工具
 */
public class SecureEncryptionUtils {

    /**
     * AES 加密
     * @param secret 密钥
     * @param originalText 原始文本
     * @return 加密后的base64 字符
     */
    public static String aesEncryption(String secret,String originalText){

        byte[] key = Base64.decode(secret);
        AES aes = SecureUtil.aes(key);
        byte[] encrypted = aes.encrypt(originalText);
        return Base64.encode(encrypted);
    }


    /**
     * AES 解密
     * @param secret 密钥
     * @param encryptedText 加密文本
     * @return 解密后原文本
     */
    public static String aesDecryption(String secret,String encryptedText){

        byte[] key = Base64.decode(secret);
        AES aes = SecureUtil.aes(key);
        return aes.decryptStr(encryptedText);
    }
}
