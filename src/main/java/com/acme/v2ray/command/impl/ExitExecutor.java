package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.command.CommandTip;
import com.acme.v2ray.command.Context;
import com.acme.v2ray.io.Tip;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class ExitExecutor implements CommandExecutor {

    public void execute(Context context, String commandBody) {
        Tip.success("即将退出！");
        System.exit(0);
    }

}
