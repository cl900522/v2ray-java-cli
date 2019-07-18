package com.acme.v2ray.command;

import com.acme.v2ray.command.impl.*;

/**
 * @author: cdchenmingxuan
 * @date: 2019/7/8 21:47
 * @description: v2rayjavacli
 */
public class CommandParser {
    public void parse(Context context, String commandLine) {
        commandLine = commandLine.trim();
        int i = commandLine.indexOf(" ");

        String commandTip = null, commandBody = null;
        if (i == -1) {
            commandTip = commandLine;
        } else {
            commandTip = commandLine.substring(0, i);
            commandBody = commandLine.substring(i, commandLine.length());
            commandBody = commandBody.trim();
        }

        CommandExecutor executor = null;
        if (CommandTip.SUB.getTip().equals(commandTip)) {
            executor = new SubExecutor();
        } else if (CommandTip.SAVE.getTip().equals(commandTip)) {
            executor = new SaveExecutor();
        } else if (CommandTip.LOAD.getTip().equals(commandTip)) {
            executor = new LoadExecutor();
        } else if (CommandTip.START.getTip().equals(commandTip)) {
            executor = new StartExecutor();
        } else if (CommandTip.LIST.getTip().equals(commandTip)) {
            executor = new ListExecutor();
        } else if (CommandTip.EXIT.getTip().equals(commandTip)) {
            executor = new ExitExecutor();
        } else if (CommandTip.STATUS.getTip().equals(commandTip)) {
            executor = new StatusExecutor();
        } else if (CommandTip.HELP.getTip().equals(commandTip)) {
            executor = new HelpExecutor();
        } else if (CommandTip.SET.getTip().equals(commandTip)) {
            executor = new SetExecutor();
        } else if (CommandTip.ENV.getTip().equals(commandTip)) {
            executor = new EnvExecutor();
        } else if (CommandTip.STOP.getTip().equals(commandTip)) {
            executor = new StopExecutor();
        } else if (CommandTip.ADD.getTip().equals(commandTip)) {
            executor = new AddExecutor();
        } else {
            executor = new HelpExecutor();
        }

        executor.execute(context, commandBody);
    }
}
