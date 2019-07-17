package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.domain.VmessServer;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.OSUtil;
import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Decoder;

import java.util.Collections;
import java.util.List;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/9 09:10
 * @description: v2rayjavacli
 */
public abstract class AbsExecutor implements CommandExecutor {
    private static final String VMESS_PRE = "vmess://";

    protected  void sortServers(List<V2rayServer> v2rayServers) {
        if(v2rayServers==null) {
            return;
        }
        Collections.sort(v2rayServers);
        int i=0;
        for (V2rayServer v2rayServer : v2rayServers) {
            v2rayServer.setIdx(i++);
        }
    }

    protected void showServers(List<V2rayServer> v2rayServers) {
        if (v2rayServers == null || v2rayServers.isEmpty()) {
            Tip.fail("没有服务器，请使用sub [url]更新订阅");
            return;
        }

        for (V2rayServer v2rayServer : v2rayServers) {
            Tip.success(v2rayServer.toString());
        }
        Tip.success("请使用select [n]选择服务的代号：");
    }

    protected VmessServer parseServer(String line) {
        if (!line.startsWith(VMESS_PRE)) {
            return null;
        }
        line = line.replace(VMESS_PRE, "");
        line = parseBase64(line);

        VmessServer v2rayServer = null;
        v2rayServer = JSON.parseObject(line, VmessServer.class);
        v2rayServer.setLine(line);
        return v2rayServer;
    }

    protected String parseBase64(String subContent) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decoderBytes = decoder.decodeBuffer(subContent);
            return new String(decoderBytes);
        } catch (Exception e) {
            Tip.fail("解析订阅的返回数据失败：" + subContent);
            e.printStackTrace();
        }
        return null;
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
