package com.acme.v2ray.domain;

import com.acme.v2ray.command.Context;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置
 *
 * @author: cdchenmingxuan
 * @date: 2019/7/17 14:06
 * @description: v2rayjavacli
 */
@Setter
@Getter
public class Config {
    public Config() {
    }

    public Config(Context context) {
        this.subUrlMap = context.getSubUrlMap();
        this.envs = context.getEnvs();
        this.servers = context.getServers();
        this.serverIdx = context.getServerIdx();
    }

    private Map<String, String> subUrlMap;

    private Integer serverIdx = 0;

    private List<V2rayServer> servers;

    private Map<String, String> envs = new HashMap<String, String>();

}
