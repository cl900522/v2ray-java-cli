package com.acme.v2ray.io;

import com.acme.v2ray.util.OSUtil;

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
        switch (OSUtil.os) {
            case LINUX:
                System.out.format("\033[36;1m%s\n\033[0m", success);
                break;
            default:
                System.out.format("%s\n", success);
                break;
        }
    }

    public static void fail(String fail) {
        switch (OSUtil.os) {
            case LINUX:
                System.out.format("\033[31;1m%s\n\033[0m", fail);
                break;
            default:
                System.out.format("%s\n", fail);
                break;
        }
    }

}
