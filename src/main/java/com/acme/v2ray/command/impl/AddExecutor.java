package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.VmessServer;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.StringUtil;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class AddExecutor extends AbsExecutor {

    public void execute(Context context, String commandBody) {
        if (StringUtil.isBlank(commandBody)) {
            Tip.fail("请提供和法律的vmess协议服务器信息");
            return;
        }

        VmessServer vmessServer = parseServer(commandBody);
        if (vmessServer == null) {
            Tip.fail("解析服务器信息失败：" + commandBody);
        }

        context.addVmessServer(vmessServer, "manual");
        Tip.success("添加服务器成功\n");

        showServers(context.getServers());
    }

}
