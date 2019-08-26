package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class DelExecutor extends AbsExecutor {

    public void execute(Context context, String commandBody) {
        if ("*".equals(commandBody)) {
            context.getSubUrlMap().clear();
            context.getServers().clear();
            return;
        }

        String[] splits = commandBody.split(" ");
        for (String split : splits) {
            if (StringUtil.isBlank(split)) {
                continue;
            }

            split = split.trim();
            if (StringUtil.isNumberInt(split)) {
                if (context.getServers() == null || context.getServers().isEmpty()) {
                    Tip.fail("没有可供删除的服务器");
                    return;
                }

                int i = Integer.parseInt(split);
                List<V2rayServer> collect = context.getServers().stream().filter((server) -> server.getIdx() != i).collect(Collectors.toList());
                context.setServers(collect);
            } else {
                final String group = split;

                context.clearGroup(group);
                context.getSubUrlMap().remove(group);
            }
        }

        sortServers(context.getServers());
    }

}
