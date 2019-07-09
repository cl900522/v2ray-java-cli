package com.acme.v2ray.util;

import java.util.Properties;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/9 20:27
 * @description: v2rayjavacli
 */
public class OSUtil {
    public static OS os = null;

    static {
        Properties props = System.getProperties(); //获得系统属性集
        String osName = props.getProperty("os.name"); //操作系统名称
        osName = osName.toLowerCase();
        if (osName.contains("linux")) {
            os = OS.LINUX;
        } else if (osName.contains("win")) {
            os = OS.WINDOWNS;
        } else if (osName.contains("mac")) {
            os = OS.MACOS;
        } else if (osName.contains("unix")) {
            os = OS.UNIX;
        }
    }

    public enum OS {
        WINDOWNS,
        LINUX,
        UNIX,
        MACOS;
    }
}
