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
public class SetExecutor implements CommandExecutor {

    public void execute(Context context, String commandBody) {
        EnvEnum editEnum = findEnvEnum(commandBody);

        if (editEnum == null) {
            Tip.fail("未发现改参数，使用env查看可配置的参数");
            return;
        }

        String replace = commandBody.replace(editEnum.getKey(), "").trim();
        if (!replace.startsWith("=") || replace.equals("=")) {
            Tip.fail("参数设置格式错误");
            return;
        }

        String value = replace.replace("=", "");
        Map<String, String> envs = context.getEnvs();
        if (envs == null) {
            envs = new HashMap<String, String>();
            context.setEnvs(envs);
        }
        envs.put(editEnum.getKey(), value);
        Tip.success(editEnum.getKey() + "设置成功!");

    }

    private EnvEnum findEnvEnum(String commandBody) {
        EnvEnum t = null;
        for (EnvEnum value : EnvEnum.values()) {
            if (commandBody.startsWith(value.getKey())) {
                t = value;
                break;
            }
        }
        return t;
    }

}
