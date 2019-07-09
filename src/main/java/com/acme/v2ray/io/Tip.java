package com.acme.v2ray.io;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/9 15:42
 * @description: v2rayjavacli
 */
public class Tip {
    public static void common(String info) {
        System.out.print(info);
    }

    public static void success(String success) {
        System.out.format("\033[36;1m%s\n\033[0m", success);
    }

    public static void fail(String fail) {
        System.out.format("\033[31;1m%s\n\033[0m", fail);
    }

}
