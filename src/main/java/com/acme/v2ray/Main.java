package com.acme.v2ray;

import com.acme.v2ray.command.CommandParser;
import com.acme.v2ray.command.Context;
import com.acme.v2ray.command.impl.LoadExecutor;
import com.acme.v2ray.io.Tip;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 入口
 *
 * @author: cdchenmingxuan
 * @date: 2019/7/8 19:33
 * @description: v2rayjavacli
 */
public class Main {
    private static CommandParser parser = new CommandParser();
    private static Context context = new Context();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            new LoadExecutor().execute(context, args[0]);
        }


        try {
            do {
                String input = waitInput(">", false);
                parser.parse(context, input);
            } while (true);
        } catch (NoSuchElementException e) {
            Tip.common("不可交换，进入后台模式运行");
            do {
                try {
                    synchronized (scanner) {
                        scanner.wait();
                    }
                } catch (Exception e1) {
                } finally {
                    scanner.notify();
                }
            } while (true);
        } catch (Exception e) {
            Tip.fail("系统错误:" + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Boolean interactAble() {
        try {
            if (System.in.available() == 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static String waitInput(String tip, Boolean newLine) {
        if (newLine != null && newLine) {
            Tip.common(tip);
        } else {
            Tip.common(tip + " ");
        }

        String in = scanner.nextLine();
        return in;
    }

}
