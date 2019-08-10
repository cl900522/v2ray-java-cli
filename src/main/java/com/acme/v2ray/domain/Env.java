package com.acme.v2ray.domain;

import java.util.HashMap;
import java.util.Map;

public class Env {
    public static final String LOCAL_SOCKS_PORT = "1080";
    public static final String LOCAL_HTTP_PORT = "1081";
    public static final String V2RAY_EXE_PATH = "v2ray";
    public static final String GLOBAL_IP = "0.0.0.0";
    public static final String NO_AUTO_START = "false";


    private Map<String, String> env;

    public Env(Map<String, String> env) {
        if (env == null) {
            env = new HashMap<>();
        }
        this.env = env;
    }

    public Integer getSocksPort() {
        String s = getEnv(EnvEnum.LOCAL_SOCKS_PORT.getKey(), LOCAL_SOCKS_PORT);
        return Integer.valueOf(s);
    }

    public Integer getHttpPort() {
        String s = getEnv(EnvEnum.LOCAL_HTTP_PORT.getKey(), LOCAL_HTTP_PORT);
        return Integer.valueOf(s);
    }

    public String getV2rayPath() {
        String s = getEnv(EnvEnum.V2RAY_PATH.getKey(), V2RAY_EXE_PATH);
        return s;
    }

    public String getBindIp() {
        String s = getEnv(EnvEnum.BIND_IP.getKey(), GLOBAL_IP);
        return s;
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
}
