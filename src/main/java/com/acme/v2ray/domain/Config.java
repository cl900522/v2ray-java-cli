package com.acme.v2ray.domain;

import com.acme.v2ray.command.Context;

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
public class Config {
    public Config() {
    }

    public Config(Context context) {
        this.subUrl = context.getSubUrl();
        this.envs = context.getEnvs();
        this.servers = context.getServers();
        this.serverIdx = context.getServerIdx();
    }

    private String subUrl;

    private Integer serverIdx = 0;

    private List<V2rayServer> servers;

    private Map<String, String> envs = new HashMap<String, String>();

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    public Integer getServerIdx() {
        return serverIdx;
    }

    public void setServerIdx(Integer serverIdx) {
        this.serverIdx = serverIdx;
    }

    public List<V2rayServer> getServers() {
        return servers;
    }

    public void setServers(List<V2rayServer> servers) {
        this.servers = servers;
    }

    public Map<String, String> getEnvs() {
        return envs;
    }

    public void setEnvs(Map<String, String> envs) {
        this.envs = envs;
    }
}
