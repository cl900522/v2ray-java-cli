package com.acme.v2ray.command;

import com.acme.v2ray.domain.Config;
import com.acme.v2ray.domain.Env;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.domain.VmessServer;
import sun.rmi.runtime.RuntimeUtil;

import java.util.ArrayList;
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

    private Integer serverIdx;

    private List<V2rayServer> servers = new ArrayList<>();

    /**
     * 变量
     */
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

    public void setServerIdx(Integer serverId) {
        this.serverIdx = serverId;
    }

    public List<V2rayServer> getServers() {
        return servers;
    }

    public void addVmessServer(VmessServer vmessServer) {
        V2rayServer v2rayServer = new V2rayServer(vmessServer);
        v2rayServer.setIdx(this.servers.size());
        this.servers.add(v2rayServer);
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

    public void load(Config config) {
        this.subUrl = config.getSubUrl();
        this.servers = config.getServers();
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.envs = config.getEnvs();
        if (this.envs == null) {
            this.envs = new HashMap<>();
        }

    }
}
