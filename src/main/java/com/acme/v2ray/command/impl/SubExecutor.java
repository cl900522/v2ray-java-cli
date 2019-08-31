package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.VmessServer;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class SubExecutor extends AbsExecutor {
    private static final int HTTP_SUCCESS = 200;

    public void execute(Context context, String commandBody) {
        String subUrl = commandBody;
        String group = "default";
        if (StringUtil.isBlank(subUrl)) {
            boolean empty = context.getSubUrlMap().isEmpty();
            if (empty) {
                Tip.fail("请提供订阅的服务器url");
                return;
            } else {
                for (Map.Entry<String, String> urlMap : context.getSubUrlMap().entrySet()) {
                    subUrl(context, urlMap.getKey(), urlMap.getValue());
                }
            }
        } else {
            boolean contains = subUrl.contains(" ");
            if (contains) {
                String[] split = subUrl.split(" ");
                group = split[0];
                subUrl = split[1].trim();
            }
            if (StringUtil.isBlank(subUrl)) {
                Tip.fail("请提供订阅的服务器url");
                return;
            }
            subUrl(context, group, subUrl);
        }

        showServers(context.getServers());
    }

    private void subUrl(Context context, String group, String subUrl) {
        String subContent = getSub(subUrl);
        String content = parseBase64(subContent);

        List<VmessServer> vmessServers = parseContentToServers(content);
        if (vmessServers.isEmpty()) {
            Tip.success("未获取到服务，可以使用sub [url]重新订阅");
            return;
        }

        context.clearGroup(group);
        for (VmessServer vmessServer : vmessServers) {
            context.addVmessServer(vmessServer, group);
        }

        context.putSubUrl(group, subUrl);
    }

    private List<VmessServer> parseContentToServers(String content) {
        List<VmessServer> servers = new ArrayList<VmessServer>();
        try {
            BufferedReader inputStream = new BufferedReader(new StringReader(content));
            String line = null;
            while ((line = inputStream.readLine()) != null) {
                if (StringUtil.isBlank(line)) {
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
            httpPost.addHeader(new BasicHeader("content-type","text/html; charset=UTF-8"));
            httpPost.addHeader(new BasicHeader("user-agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36"));
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
