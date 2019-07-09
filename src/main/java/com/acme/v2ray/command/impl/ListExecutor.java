package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class ListExecutor extends AbsExecutor {

    public void execute(Context context, String commandBody) {
        showServers(context.getServers());
    }

}
