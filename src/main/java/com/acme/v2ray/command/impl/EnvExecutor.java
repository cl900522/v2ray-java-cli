package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.EnvEnum;
import com.acme.v2ray.io.Tip;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class EnvExecutor implements CommandExecutor {

    public void execute(Context context, String commandBody) {
        Tip.success("参数设置如下：");
        Map<String, String> envs = context.getEnvs();
        if (envs == null) {
            envs = new HashMap<>();
        }
        for (EnvEnum value : EnvEnum.values()) {
            Tip.success(value.getKey() + "（" + value.getRemark() + ")：" + envs.get(value.getKey()));
        }
    }

}
