package com.acme.v2ray.command;

import com.acme.v2ray.domain.Env;
import com.acme.v2ray.domain.V2rayServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:29
 * @description: v2rayjavacli
 */
public class Context {

    private Boolean started = false;

    private String subUrl;

    private String serverName;

    private List<V2rayServer> servers;

    private Map<String, String> envs = new HashMap<String, String>();

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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

    public Env buildEnv() {
        return new Env(envs);
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }
}
