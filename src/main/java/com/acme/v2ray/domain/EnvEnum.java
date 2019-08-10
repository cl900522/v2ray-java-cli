package com.acme.v2ray.domain;

public enum EnvEnum {
    BIND_IP("bind.ip", "绑定的网卡ip 0.0.0.0"),
    LOCAL_SOCKS_PORT("local.socksPort", "本地代理socks端口 1080"),
    LOCAL_HTTP_PORT("local.httpPort", "本地代理http端口 1081"),
    AUTO_START("auto.start", "是否自动启动v2ray服务 true|false"),
    V2RAY_PATH("v2ray.path", "v2ray可执行文件路径");


    private String key;
    private String remark;

    EnvEnum(String key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

    public String getRemark() {
        return remark;
    }
}
