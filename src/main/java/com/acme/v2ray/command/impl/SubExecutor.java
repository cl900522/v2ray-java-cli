package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.io.Tip;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class SubExecutor extends AbsExecutor {
    private static final int HTTP_SUCCESS = 200;

    public void execute(Context context, String commandBody) {
        String subUrl = commandBody;
        if (subUrl == null || subUrl.trim().equals("")) {
            subUrl = context.getSubUrl();
        }

        if (subUrl == null || subUrl.trim().equals("")) {
            Tip.fail("请提供订阅的服务器url");
            return;
        }

        String subContent = getSub(subUrl);
        String content = parseBase64(subContent);

        List<V2rayServer> v2rayServers = parseContentToServers(content);
        if (v2rayServers.isEmpty()) {
            Tip.success("未获取到服务，可以使用sub [url]重新订阅");
            return;
        }

        showServers(v2rayServers);

        context.setServers(v2rayServers);
        context.setSubUrl(subUrl);
    }

    private List<V2rayServer> parseContentToServers(String content) {
        List<V2rayServer> servers = new ArrayList<V2rayServer>();
        try {
            BufferedReader inputStream = new BufferedReader(new StringReader(content));
            String line = null;
            while ((line = inputStream.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }
                if (!line.startsWith("vmess://")) {
                    continue;
                }
                line = line.replace("vmess://", "");
                line = parseBase64(line);

                V2rayServer v2rayServer = parseServer(line);
                servers.add(v2rayServer);
            }

        } catch (Exception e) {
            Tip.fail("解析服务器信息失败");
            e.printStackTrace();
        }
        return servers;
    }

    private V2rayServer parseServer(String line) {
        V2rayServer v2rayServer = null;
        v2rayServer = JSON.parseObject(line, V2rayServer.class);
        v2rayServer.setLine(line);
        return v2rayServer;
    }

    private String parseBase64(String subContent) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decoderBytes = decoder.decodeBuffer(subContent);
            return new String(decoderBytes);
        } catch (Exception e) {
            Tip.fail("解析订阅的返回数据失败：" + subContent);
            e.printStackTrace();
        }
        return null;
    }

    private static String getSub(String url) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpresponse = null;
        try {
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(URI.create(url));
            httpresponse = httpclient.execute(httpPost);

            if (httpresponse.getStatusLine().getStatusCode() != HTTP_SUCCESS) {
                HttpEntity entity = httpresponse.getEntity();
                EntityUtils.consume(entity);
                Tip.fail("获取订阅失败 [" + url + "]");
                return null;
            }

            return EntityUtils.toString(httpresponse.getEntity());
        } catch (Exception e) {
            Tip.fail("获取订阅的服务器信息失败！");
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (httpresponse != null) {
                    httpresponse.close();
                }
            } catch (Exception e) {
            }
            try {
                httpclient.close();
            } catch (Exception e) {
            }

        }
    }

}
