package com.acme.v2ray.command;

import lombok.Getter;

/**
 * ${DESC}
 *
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:20
 * @description: v2rayjavacli
 */
@Getter
public enum CommandTip {
    HELP("help", "help", "获取命令帮助"),
    SUB("sub", "sub [group] [url]", "重新刷新订阅url，并保存group的订阅分组"),
    ADD("add", "add vmess://xxxx", "添加服务器，服务器分组为manual"),
    DEL("del", "del *, del 1, del group", "清除服务器或者组"),
    SAVE("save", "save [path/to/config]", "保存当前服务器信息到.config"),
    LOAD("load", "load [path/to/config]", "从保存的.config文件恢复信息"),
    START("start", "start [index]", "选择服务器编号"),
    STOP("stop", "stop", "关闭代理服务"),
    LIST("list", "list", "显示服务器列表"),
    STATUS("status", "status", "显示服务状态"),
    SET("set", "set [k]=[v]", "设置参数"),
    ENV("env", "env", "显示参数"),
    EXIT("exit", "exit", "退出命令");

    private String tip;

    private String usage;

    private String remark;

    CommandTip(String tip, String usage, String remark) {
        this.tip = tip;
        this.usage = usage;
        this.remark = remark;
    }

}
