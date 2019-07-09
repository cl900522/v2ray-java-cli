package com.acme.v2ray.command;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:25
 * @description: v2rayjavacli
 */
public interface CommandExecutor {
    void execute(Context context, String commandBody);

}
