package com.acme.v2ray;

import com.acme.v2ray.command.CommandParser;
import com.acme.v2ray.command.Context;
import com.acme.v2ray.command.impl.LoadExecutor;
import com.acme.v2ray.io.Tip;

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
        } finally {
            scanner.close();
        }
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
