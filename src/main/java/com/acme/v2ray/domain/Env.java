package com.acme.v2ray.domain;

import java.util.HashMap;
import java.util.Map;

public class Env {
    public static final Integer LOCAL_PORT = 1080;
    public static final String V2RAY_EXE_PATH = "v2ray";


    private Map<String, String> env;

    public Env(Map<String, String> env) {
        if (env == null) {
            env = new HashMap<>();
        }
        this.env = env;
    }

    public Integer getLocalPort() {
        String s = env.get(EnvEnum.LOCAL_PORT.getKey());
        if (s == null) {
            return LOCAL_PORT;
        }
        return Integer.valueOf(s);
    }

    public String getV2rayPath() {
        String s = env.get(EnvEnum.V2RAY_PATH.getKey());
        if (s == null) {
            return V2RAY_EXE_PATH;
        }
        return s;
    }
}
