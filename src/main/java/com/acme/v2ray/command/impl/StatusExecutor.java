package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.command.Context;
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
        Tip.success("本地代理端口：" + context.getProxyPort());
        Tip.success("---------------------------------------");
    }

}
