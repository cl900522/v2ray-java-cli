package com.acme.v2ray.domain;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 19:57
 * @description: v2rayjavacli
 */
public class V2rayServer implements Comparable {
    private static String SERVER_FORMAT = "【%d】%s - (%s:%s)";

    public V2rayServer() {
    }

    public V2rayServer(VmessServer vmessServer) {
        this.name = vmessServer.getPs();
        this.host = vmessServer.getAdd();
        this.port = Integer.valueOf(vmessServer.getPort());
        this.userId = vmessServer.getId();
        this.email = "t@t.tt";
        this.net = vmessServer.getNet();
    }

    private Integer idx = 0;

    private String name;
    private String host;
    private Integer port;
    private String userId;
    private String email;
    private String net;

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    @Override
    public String toString() {
        return String.format(SERVER_FORMAT, idx, name, host, port);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        if (!(o instanceof V2rayServer)) {
            return 1;
        }
        V2rayServer to = (V2rayServer) o;
        if (this.idx == null) {
            this.idx = 0;
        }
        if (to.idx == null) {
            to.idx = 0;
        }

        return this.idx - to.idx;
    }
}
