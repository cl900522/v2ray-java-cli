package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.Context;
import com.acme.v2ray.domain.Env;
import com.acme.v2ray.domain.V2rayServer;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.RuntimeUtil;
import com.acme.v2ray.util.StreamUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class SelectExecutor extends AbsExecutor {
    private static final String COMMAND_FORMAT = "%s -config %s";
    private static Pattern pattern = Pattern.compile("[0-9]*");

    public void execute(Context context, String commandBody) {
        List<V2rayServer> v2rayServers = context.getServers();
        if (v2rayServers == null || v2rayServers.isEmpty()) {
            Tip.fail("没有服务器，请先使用sub [url]订阅");
            return;
        }

        if (commandBody == null || commandBody.trim().equals("")) {
            Tip.fail("请选择服务器编号：[0-" + (v2rayServers.size() - 1) + "]");
            return;
        }

        Boolean isCommand = pattern.matcher(commandBody).find();
        if (!isCommand) {
            return;
        }

        int i = Integer.parseInt(commandBody);
        if (i >= v2rayServers.size() || i < 0) {
            Tip.fail("请选择服务器编号：[0-" + (v2rayServers.size() - 1) + "]");
            return;
        }

        V2rayServer v2rayServer = v2rayServers.get(i);
        if (v2rayServer == null) {
            return;
        }
        context.setServerIdx(i);

        String v2rayConfigPath = createV2rayConfigFile(context, v2rayServer);
        if (v2rayConfigPath == null || v2rayConfigPath.trim().equals("")) {
            Tip.fail("生成启动v2ray服务配置文件失败");
            return;
        }

        try {
            RuntimeUtil.killv2rayServer();
            Tip.success("检查并关闭遗留v2ray服务");

            Env env = context.buildEnv();

            String command = String.format(COMMAND_FORMAT, env.getV2rayPath(), v2rayConfigPath);
            Tip.common("运行：" + command + "\n");

            RuntimeUtil.run(command, true);
            context.setStarted(true);

            if (env.openSystemProxy()) {
                RuntimeUtil.closeSysProxy();
                RuntimeUtil.openSysProxy("socks", "127.0.0.1:" + env.getLocalPort());
            }
            Tip.success("本地代理服务启动成功");
        } catch (Exception e) {
            Tip.fail("启动服务失败：");
            e.printStackTrace();
        }
    }

    private String createV2rayConfigFile(Context context, V2rayServer v2rayServer) {
        String content = null;
        Env env = context.buildEnv();

        try {
            InputStream resourceInStream = this.getClass().getResourceAsStream("/v2ray-config.json.tpl");
            String template = StreamUtil.toString(resourceInStream);
            template = template.replace("${server.port}", v2rayServer.getPort().toString());
            template = template.replace("${server.host}", v2rayServer.getHost());
            template = template.replace("${server.id}", v2rayServer.getUserId());
            template = template.replace("${server.email}", "t@t.tt");
            template = template.replace("${server.network}", v2rayServer.getNet());
            template = template.replace("${local.port}", String.valueOf(env.getLocalPort()));
            content = template;
        } catch (Exception e) {
            Tip.fail("生成v2ray配置文件内容失败");
            e.printStackTrace();
            return null;
        }

        if (content == null || content.trim().equals("")) {
            return null;
        }

        try {
            File configFile = new File(".servers/" + v2rayServer.getHost() + "-" + v2rayServer.getPort() + ".json");
            if (!configFile.exists()) {
                File dir = new File(".servers/");
                if (!dir.exists()) {
                    dir.mkdir();
                }
                configFile.createNewFile();
            }

            StreamUtil.writeTo(content, new FileOutputStream(configFile));
            return configFile.getAbsolutePath();
        } catch (Exception e) {
            Tip.fail("生成v2ray配置文件失败");
            e.printStackTrace();
        }

        return null;
    }

}
