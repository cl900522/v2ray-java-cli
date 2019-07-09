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
public class HelpExecutor implements CommandExecutor {
    private static CommandTip[] tips = CommandTip.values();
    private static String tipFormat = "【%d】%s : %s (%s)";

    public void execute(Context context, String commandBody) {
        Tip.success("请按照以下方式输入命令：");
        Integer i = 0;
        for (CommandTip tip : tips) {
            Tip.success(String.format(tipFormat, i, tip.getTip(), tip.getUsage(), tip.getRemark()));
            i++;
        }
    }

}
