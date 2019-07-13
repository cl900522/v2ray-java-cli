package com.acme.v2ray.domain;

public enum EnvEnum {
    LOCAL_PORT("local.port", "本地代理端口"),
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
