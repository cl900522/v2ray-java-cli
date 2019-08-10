{
  "log": {
    "access": "access.log",
    "error": "error.log",
    "loglevel": "warning"
  },
  "inbounds": [{
    "port": ${local.socksPort},
    "listen": "${bind.ip}",
    "protocol": "socks",
    "sniffing": {
      "enabled": false,
      "destOverride": ["http", "tls"]
    },
    "settings": {
      "auth": "noauth",
      "udp": true,
      "ip": "127.0.0.1",
      "clients": null
    },
    "tag": "in1",
    "streamSettings": null
  }, {
    "port": ${local.httpPort},
    "listen": "${bind.ip}",
    "protocol": "http",
    "sniffing": {
      "enabled": false,
      "destOverride": ["http", "tls"]
    },
    "settings": {
      "auth": "noauth",
      "udp": true,
      "ip": "127.0.0.1",
      "clients": null
    },
    "tag": "in2",
    "streamSettings": null
  }],
  "outbound": {
    "tag": "agentout",
    "protocol": "vmess",
    "settings": {
      "vnext": [
        {
          "address": "${server.host}",
          "port": ${server.port},
          "users": [
            {
              "id": "${server.id}",
              "alterId": 2,
              "email": "${server.email}",
              "security": "aes-128-gcm"
            }
          ]
        }
      ],
      "servers": null
    },
    "streamSettings": {
      "network": "${server.network}",
      "security": "tls",
      "tlsSettings": null,
      "tcpSettings": null,
      "kcpSettings": null,
      "wsSettings": null,
      "httpSettings": null
    },
    "mux": {
      "enabled": false
    }
  },
  "inboundDetour": null,
  "outboundDetour": [
    {
      "protocol": "freedom",
      "settings": {
        "response": null
      },
      "tag": "direct"
    },
    {
      "protocol": "blackhole",
      "settings": {
        "response": {
          "type": "http"
        }
      },
      "tag": "blockout"
    }
  ],
  "dns": {
    "servers": [
      "8.8.8.8",
      "8.8.4.4",
      "localhost"
    ]
  },
  "routing": {
    "strategy": "rules",
    "settings": {
      "domainStrategy": "IPIfNonMatch",
      "rules": [
        {
          "type": "field",
          "port": null,
          "outboundTag": "direct",
          "ip": null,
          "domain": [
            "geosite:cn"
          ]
        },
        {
          "type": "field",
          "port": null,
          "outboundTag": "direct",
          "ip": [
            "geoip:cn"
          ],
          "domain": null
        },
        {
          "type": "field",
          "port": null,
          "outboundTag": "direct",
          "ip": [
            "0.0.0.0/8",
            "10.0.0.0/8",
            "100.64.0.0/10",
            "127.0.0.0/8",
            "169.254.0.0/16",
            "172.16.0.0/12",
            "192.0.0.0/24",
            "192.0.2.0/24",
            "192.168.0.0/16",
            "198.18.0.0/15",
            "198.51.100.0/24",
            "203.0.113.0/24",
            "::1/128",
            "fc00::/7",
            "fe80::/10"
          ],
          "domain": null
        }
      ]
    }
  }
}