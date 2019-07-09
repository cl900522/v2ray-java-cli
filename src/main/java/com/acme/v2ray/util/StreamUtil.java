package com.acme.v2ray.util;

import com.acme.v2ray.io.Tip;

import java.io.*;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/9 10:41
 * @description: v2rayjavacli
 */
public class StreamUtil {
    public static String toString(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            Tip.fail("读取流失败");
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void writeTo(String content, OutputStream outputStream) {
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(outputStream);
            writer.write(content);
        } catch (Exception e) {
            Tip.fail("流写入错误");
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void writeTo(String content, Writer writer) {
        try {
            writer.write(content);
        } catch (Exception e) {
            Tip.fail("流写入错误");
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
