package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.RuntimeUtil;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class StopExecutor extends AbsExecutor {

    public void execute(Context context, String commandBody) {
        RuntimeUtil.killv2rayServer();
        Tip.success("服务已关闭！");
    }

}
