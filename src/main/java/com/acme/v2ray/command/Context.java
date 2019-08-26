package com.acme.v2ray.command;

import com.acme.v2ray.domain.Config;
import com.acme.v2ray.domain.Env;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.domain.VmessServer;
import com.acme.v2ray.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:29
 * @description: v2rayjavacli
 */
@Getter
@Setter
public class Context {

    private Boolean started = false;

    private String configPath = ".config";

    private Map<String, String> subUrlMap = new HashMap<>();

    private Integer serverIdx;

    private List<V2rayServer> servers = new ArrayList<>();

    /**
     * 变量
     */
    private Map<String, String> envs = new HashMap<String, String>();

    public void putSubUrl(String group, String url) {
        this.subUrlMap.put(group, url);
    }

    public void addVmessServer(VmessServer vmessServer, String group) {
        V2rayServer v2rayServer = new V2rayServer(vmessServer);
        v2rayServer.setGroup(group);
        v2rayServer.setIdx(this.servers.size());
        this.servers.add(v2rayServer);
    }

    public Env buildEnv() {
        return new Env(envs);
    }

    public void load(Config config) {
        this.subUrlMap = config.getSubUrlMap() == null ? new HashMap<>() : config.getSubUrlMap();
        this.serverIdx = config.getServerIdx();

        this.servers = config.getServers();
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.envs = config.getEnvs();
        if (this.envs == null) {
            this.envs = new HashMap<>();
        }

    }

    public void clearGroup(String group) {
        if (StringUtil.isBlank(group)) {
            return;
        }
        if (servers == null || servers.isEmpty()) {
            return;
        }
        Stream<V2rayServer> stream = servers.stream();
        servers = stream.filter((server) -> !group.equals(server.getGroup())).collect(Collectors.toList());
    }
}
