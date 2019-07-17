package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.Config;
import com.acme.v2ray.domain.Env;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.StreamUtil;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class LoadExecutor implements CommandExecutor {

    public void execute(Context context, String commandBody) {
        String configPath = commandBody;
        if (commandBody == null || commandBody.trim().equals("")) {
            configPath = context.getConfigPath();
        }

        File file = new File(configPath);
        try {
            if (!file.exists() || !file.isFile()) {
                Tip.fail("配置文件不存在");
                return;
            }

            context.setConfigPath(commandBody);

            String bckConfigContent = StreamUtil.toString(new FileInputStream(file));
            Config bckConfig = JSON.parseObject(bckConfigContent, Config.class);
            if (bckConfig != null) {
                context.load(bckConfig);
            }
            Tip.success("恢复归档成功:" + file.getAbsolutePath());

            /*根据配置判断是否需要自动启动*/
            Env evn = context.buildEnv();
            if (evn.autoStart() && context.getServerIdx() != null) {
                new SelectExecutor().execute(context, context.getServerIdx().toString());
            }
        } catch (Exception e) {
            Tip.fail("恢复归档失败");
            e.printStackTrace();
        } finally {
        }
    }

}
