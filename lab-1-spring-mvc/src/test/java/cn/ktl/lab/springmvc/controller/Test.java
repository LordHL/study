package cn.ktl.lab.springmvc.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author lin ho
 * Des: TODO
 */
public class Test {
    public static void main(String[] args) {
        String str = "1";
        String[] split = str.split(",");
        String collect = Arrays.stream(split).collect(Collectors.joining(","));
        System.out.println(split[0]);
        System.out.println("collect : " + collect);
    }
}
