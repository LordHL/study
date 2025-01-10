package cn.ktl.lab.springmvc.controller;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author lin ho
 * Des: TODO
 */
public class Test {
    public static void main(String[] args) {
        String string = generateUsername("1515604173@qq.com");
        System.out.println(string);
    }

    private static String generateUsername(String email) {
        String emailUsername = email.split("@")[0];
        emailUsername = emailUsername.replaceAll("[^A-Za-z0-9]", "");
        String randomNumber = RandomStringUtils.randomNumeric(6);
        String username = emailUsername.substring(0, emailUsername.length()) + randomNumber;

        return username;
    }
}
