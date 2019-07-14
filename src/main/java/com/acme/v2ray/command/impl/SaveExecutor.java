package com.acme.v2ray.command.impl;

import com.acme.v2ray.command.CommandExecutor;
import com.acme.v2ray.command.Context;
import com.acme.v2ray.io.Tip;
import com.acme.v2ray.util.StreamUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.io.FileWriter;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:26
 * @description: v2rayjavacli
 */
public class SaveExecutor implements CommandExecutor {

    public void execute(Context context, String commandBody) {
        File file = new File(".config");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            StreamUtil.writeTo(JSON.toJSONString(context, SerializerFeature.PrettyFormat), fileWriter);
            Tip.success("保存归档成功:" + file.getAbsolutePath());
        } catch (Exception e) {
            Tip.fail("保存归档:" + file.getAbsolutePath() + "失败");
            e.printStackTrace();
        } finally {
        }
    }

}