package com.acme.v2ray.command;

/**
 * ${DESC}
 *
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:20
 * @description: v2rayjavacli
 */
public enum CommandTip {
    HELP("help", "help", "获取命令帮助"),
    SUB("sub", "sub [url]", "重新刷新订阅url"),
    ADD("add", "add vmess://xxxx", "添加服务器"),
    SAVE("save", "save", "保存当前服务器信息到.config"),
    LOAD("load", "load", "从保存的.config文件恢复信息"),
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
