package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.OSUtil;

import java.util.List;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/9 09:10
 * @description: v2rayjavacli
 */
public abstract class AbsExecutor implements CommandExecutor {
    private static String serverFormat = "【%d】%s - (%s:%s)";

    protected void showServers(List<V2rayServer> v2rayServers) {
        if (v2rayServers == null || v2rayServers.isEmpty()) {
            Tip.fail("没有服务器，请使用sub [url]更新订阅");
            return;
        }

        Integer index = 0;
        for (V2rayServer v2rayServer : v2rayServers) {
            Tip.success(String.format(serverFormat, index, v2rayServer.getPs(), v2rayServer.getAdd(), v2rayServer.getPort()));
            index++;
        }
        Tip.success("请使用select [n]选择服务的代号：");
    }


    protected void killv2rayServer() {
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
        }
    }
}
