package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.Env;
import com.acme.v2ray.io.Tip;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class StatusExecutor implements CommandExecutor {

    public void execute(Context context, String commandBody) {
        Tip.success("---------------------------------------");

        Tip.success("订阅服务器地址：" + context.getSubUrl());
        Tip.success("可用服务器总数：" + (context.getServers() == null ? 0 : context.getServers().size()));
        Tip.success("代理状态：" + (context.getStarted() ? "【启动】" : "【关闭】"));

        Tip.success("---------------------------------------");
        if (context.getStarted()) {
            Env env = context.buildEnv();
            Tip.success("已选择服务器名称：" + context.getServers().get(context.getServerIdx()).toString());
            Tip.success("代理端口：" + env.getLocalPort());
            Tip.success("代理协议：" + env.getProtocol());
        }

    }

}
