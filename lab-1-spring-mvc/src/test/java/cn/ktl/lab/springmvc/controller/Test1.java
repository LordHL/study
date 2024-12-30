package cn.ktl.lab.springmvc.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * @Author lin ho
 * Des: TODO
 */
public class Test1 {


    public static void main(String[] args) {
        // AES 密钥
//        String key = "1234567812345678"; // 示例密钥
        byte[] key = new byte[16]; // 16字节（128位）
        new SecureRandom().nextBytes(key);
        String base64Key = Base64.encode(key);
        System.out.println("base64Key : " + base64Key);
        AES aes = SecureUtil.aes(key);

        // 原始银行卡号
        String cardNumber = "1234567890123456";

        // 加密银行卡号
        String encryptedCardNumber = encryptCardNumber(cardNumber, aes);
        System.out.println("加密后的银行卡号：" + encryptedCardNumber);

        // 假设你将加密后的银行卡号存储到数据库中
        // 例如：通过数据库存储查询返回加密结果

        // 解密银行卡号
//        String decryptedCardNumber = decryptCardNumber(encryptedCardNumber, aes);
//        System.out.println("解密后的银行卡号：" + decryptedCardNumber);
    }

    public static String encryptCardNumber(String cardNumber, AES aes) {
        byte[] encryptedBytes = aes.encrypt(cardNumber.getBytes(StandardCharsets.UTF_8));

        StringBuilder encryptedNumber = new StringBuilder();
        for (byte b : encryptedBytes) {
            int digit = (b & 0xFF) % 10;
            encryptedNumber.append(digit);
            if (encryptedNumber.length() == cardNumber.length()) {
                break;
            }
        }
        return encryptedNumber.toString();
    }

    public static String decryptCardNumber(String encryptedCardNumber, AES aes) {
        byte[] encryptedBytes = new byte[encryptedCardNumber.length()];
        for (int i = 0; i < encryptedCardNumber.length(); i++) {
            encryptedBytes[i] = (byte) ((encryptedCardNumber.charAt(i) - '0') + 128);
        }

        byte[] decryptedBytes = aes.decrypt(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }


}
