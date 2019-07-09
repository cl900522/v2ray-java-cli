package com.acme.v2ray.command;

import com.acme.v2ray.domain.V2rayServer;

import java.util.List;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:29
 * @description: v2rayjavacli
 */
public class Context {
    public static final String V2RAY_EXE_PATH = "C:/Program File/V2Ray";

    private Integer proxyPort = 1080;

    private String subUrl;

    private String serverId;

    private String v2rayPath = V2RAY_EXE_PATH;

    private List<V2rayServer> servers;

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getV2rayPath() {
        return v2rayPath;
    }

    public void setV2rayPath(String v2rayPath) {
        if (v2rayPath == null || v2rayPath.trim().equals("")) {
            v2rayPath = V2RAY_EXE_PATH;
        }
        this.v2rayPath = v2rayPath;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public List<V2rayServer> getServers() {
        return servers;
    }

    public void setServers(List<V2rayServer> servers) {
        this.servers = servers;
    }
}
