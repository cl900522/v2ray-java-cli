package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.domain.VmessServer;
import com.acme.v2ray.io.Tip;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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

        List<VmessServer> vmessServers = parseContentToServers(content);
        if (vmessServers.isEmpty()) {
            Tip.success("未获取到服务，可以使用sub [url]重新订阅");
            return;
        }

        for (VmessServer vmessServer : vmessServers) {
            context.addVmessServer(vmessServer);
        }

        showServers(context.getServers());
        context.setSubUrl(subUrl);
    }

    private List<VmessServer> parseContentToServers(String content) {
        List<VmessServer> servers = new ArrayList<VmessServer>();
        try {
            BufferedReader inputStream = new BufferedReader(new StringReader(content));
            String line = null;
            while ((line = inputStream.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                VmessServer v2rayServer = parseServer(line);
                servers.add(v2rayServer);
            }

        } catch (Exception e) {
            Tip.fail("解析服务器信息失败");
            e.printStackTrace();
        }
        return servers;
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
