package cn.ktl.lab.springmvc.utils;



import cn.ktl.lab.springmvc.exception.BusinessException;

import java.util.regex.Pattern;

import static cn.ktl.lab.springmvc.exception.UmErrorCodeEnum.UM_PASSWORD_FORMAT_INCORRECT;


/**
 * @Author lin ho
 * Des: 校验密码的工具类
 */
public class PasswordValidatorUtils {

    // 正则表达式，用于匹配不同字符类别
    /**
     * Password Length:
     * Minimum: 8 characters
     * Maximum: 64 characters
     * Complexity Requirements:
     * The password should contain characters from three of the following categories:
     * - At least 1 Uppercase letter(A through Z).
     * - At least 1 Lowercase letter (a through z).
     * - At least 1 Base 10 digit (0 through 9).
     * - At least 1 Special character: '-!"#$%&()*,./:;?@[]^_`{|}~+<=>
     * Unicode character is not allowed.
     */
    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[-!\"#$%&'()*,./:;?@\\[\\]^_`{|}~+<=]");


    // 校验密码
    public static boolean isValidPassword(String password) {
        // 1. 校验密码长度
        if (password == null || password.length() < 8 || password.length() > 64) {
            return false;
        }

        // 2. 检查字符类别计数
        int categoryCount = 0;
        if (UPPER_CASE.matcher(password).find()) {
            categoryCount++;
        }
        if (LOWER_CASE.matcher(password).find()) {
            categoryCount++;
        }
        if (DIGIT.matcher(password).find()) {
            categoryCount++;
        }
        if (SPECIAL_CHAR.matcher(password).find()) {
            categoryCount++;
        }

        // 3. 确保至少有三类字符
        if (categoryCount < 3) {
            throw BusinessException.of(UM_PASSWORD_FORMAT_INCORRECT);
        }
        return Boolean.TRUE;
    }


}
