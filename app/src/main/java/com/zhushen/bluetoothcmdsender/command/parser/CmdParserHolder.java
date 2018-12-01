package com.zhushen.bluetoothcmdsender.command.parser;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhushen on 2018/3/14.
 */

public class CmdParserHolder {
    public static final String CMD_END = SerialCommand.END;
    public static final String CMD_START = SerialCommand.START;

    private static Map<String, CmdParser> MAP = new HashMap<>();
    private static CmdParser PARSER = null;

    static {
//        MAP.put("CPRParser",new CPRParser(CPRController.getInstance().getDataHelper()));

        CmdParser cur = null;
        for (Map.Entry<String, CmdParser> entry : MAP.entrySet()) {
            if (null == cur) {
                cur = entry.getValue();
                PARSER = cur;
            } else {
                cur.setNextParser(entry.getValue());
                cur = cur.getNextParser();
            }
        }
    }

    public static void parse(String cmd){
        try {
            String cmds[] = cmd.split("FC");
            for (String command:cmds) {
                PARSER.parser(new SerialCommand(command + "FC"));
                Log.d("zhushen","parser cmd and cmd = " + command + "FC");
            }
        }catch (Exception e){
            Log.e("invalid cmd", cmd);
        }
    }

}
