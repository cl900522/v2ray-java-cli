package com.acme.v2ray.util;

import com.acme.v2ray.io.Tip;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/17 16:32
 * @description: v2rayjavacli
 */
public class RuntimeUtil {

    public static void run(String command, Boolean backgroup) {
        try {
            Process exec = null;
            switch (OSUtil.os) {
                case WINDOWNS:
                    if (!backgroup) {
                        exec = Runtime.getRuntime().exec(command);
                    } else {
                        exec = Runtime.getRuntime().exec(command + "&");
                    }
                    break;
                default:
                    if (!backgroup) {
                        exec = Runtime.getRuntime().exec(command);
                    } else {
                        exec = Runtime.getRuntime().exec("nohup " + command + " &");
                    }
                    break;
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            Tip.fail("运行命令失败：" + command);
            e.printStackTrace();
        }
    }

    public static void openSysProxy(String proxy, String url) {
        try {
            switch (OSUtil.os) {
                case WINDOWNS:
                    break;
                default:
                    switch (proxy) {
                        case "http":
                        case "https":
                            Runtime.getRuntime().exec("export all_proxy=http://" + url);
                            Runtime.getRuntime().exec("export ALL_PROXY=http://" + url);

                            Runtime.getRuntime().exec("export http_proxy=http://" + url);
                            Runtime.getRuntime().exec("export HTTP_PROXY=http://" + url);

                            Runtime.getRuntime().exec("export https_proxy=http://" + url);
                            Runtime.getRuntime().exec("export HTTPS_PROXY=http://" + url);

                            Runtime.getRuntime().exec("export socks_proxy=http://" + url);
                            Runtime.getRuntime().exec("export SOCKS_PROXY=http://" + url);
                            break;
                        case "socks":
                        case "socks5":
                            Runtime.getRuntime().exec("export all_proxy=socks://" + url);
                            Runtime.getRuntime().exec("export ALL_PROXY=socks://" + url);

                            Runtime.getRuntime().exec("export http_proxy=socks://" + url);
                            Runtime.getRuntime().exec("export HTTP_PROXY=socks://" + url);

                            Runtime.getRuntime().exec("export https_proxy=socks://" + url);
                            Runtime.getRuntime().exec("export HTTPS_PROXY=socks://" + url);

                            Runtime.getRuntime().exec("export socks_proxy=socks://" + url);
                            Runtime.getRuntime().exec("export SOCKS_PROXY=socks://" + url);
                            break;
                        default:
                            Tip.fail("不支持的代理方式：" + proxy);
                            break;
                    }
                    break;
            }
            Runtime.getRuntime().exec("export NO_PROXY=localhost,127.0.0.1,192.168.0.0");
            Runtime.getRuntime().exec("export no_proxy=localhost,127.0.0.1,192.168.0.0");
            Thread.sleep(1000);
        } catch (Exception e) {
            Tip.fail("打开系统代理失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void closeSysProxy() {
        try {
            switch (OSUtil.os) {
                case WINDOWNS:
                    break;
                default:
                    Runtime.getRuntime().exec("unset all_proxy");
                    Runtime.getRuntime().exec("unset ALL_PROXY");

                    Runtime.getRuntime().exec("unset https_proxy");
                    Runtime.getRuntime().exec("unset HTTPS_PROXY");

                    Runtime.getRuntime().exec("unset http_proxy");
                    Runtime.getRuntime().exec("unset HTTP_PROXY");

                    Runtime.getRuntime().exec("unset socks_proxy");
                    Runtime.getRuntime().exec("unset SOCKS_PROXY");

                    Runtime.getRuntime().exec("unset no_proxy");
                    Runtime.getRuntime().exec("unset NO_PROXY");
                    break;
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            Tip.fail("关闭系统代理失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void killv2rayServer() {
        try {
            switch (OSUtil.os) {
                case WINDOWNS:
                    Runtime.getRuntime().exec("taskkill /im v2ray.exe /f");
                    break;
                default:
                    Runtime.getRuntime().exec("killall v2ray");
                    break;
            }
        } catch (Exception e) {
            Tip.fail("关闭V2ray服务失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
