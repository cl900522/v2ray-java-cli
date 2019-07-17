package com.acme.v2ray.domain;

import java.util.HashMap;
import java.util.Map;

public class Env {
    public static final String LOCAL_PORT = "1080";
    public static final String V2RAY_EXE_PATH = "v2ray";
    public static final String NO_OPEN = "false";
    public static final String NO_AUTO_START = "false";
    public static final String DEFAULT_PROTOCOL = "socks";


    private Map<String, String> env;

    public Env(Map<String, String> env) {
        if (env == null) {
            env = new HashMap<>();
        }
        this.env = env;
    }

    public Integer getLocalPort() {
        String s = getEnv(EnvEnum.LOCAL_PORT.getKey(), LOCAL_PORT);
        return Integer.valueOf(s);
    }

    public String getV2rayPath() {
        String s = getEnv(EnvEnum.V2RAY_PATH.getKey(), V2RAY_EXE_PATH);
        return s;
    }

    public Boolean openSystemProxy() {
        String s = getEnv(EnvEnum.SYSTEM_PROXY.getKey(), NO_OPEN);
        return Boolean.valueOf(s);
    }

    public Boolean autoStart() {
        String s = getEnv(EnvEnum.AUTO_START.getKey(), NO_AUTO_START);
        return Boolean.valueOf(s);
    }

    private String getEnv(String key, String dfalut) {
        String s = env.get(key);
        if (s == null || s.trim().equals("")) {
            env.put(key, dfalut);
            return dfalut;
        } else {
            return s;
        }
    }

    public String getProtocol() {
        String s = env.get(EnvEnum.PROXY_RROTOCOL.getKey());
        if (s == null) {
            return DEFAULT_PROTOCOL;
        }
        return s;
    }
}
