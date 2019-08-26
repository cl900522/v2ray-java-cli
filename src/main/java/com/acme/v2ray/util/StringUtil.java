package com.acme.v2ray.util;

import java.util.regex.Pattern;

/**
 * @author: cdchenmingxuan
 * @date: 2019/8/26 15:46
 * @description: v2rayjavacli
 */
public class StringUtil {
    private static final Pattern NUM_INT_PATTERN = Pattern.compile("^[0-9]{1,}$");

    public static boolean isNumberInt(String input) {
        return NUM_INT_PATTERN.matcher(input).find();
    }

    public static boolean isNotBlank(String input) {
        return !isBlank(input);
    }

    public static boolean isBlank(String input) {
        return input == null || input.trim().equals("");
    }
}
