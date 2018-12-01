package com.zhushen.bluetoothcmdsender.command.parser;


/**
 * Created by Zhushen on 2018/3/15.
 */

public class AEDParser extends CmdParser {
//    AEDHelper helper;
    private String header = "08";

    @Override
    protected boolean needParser(SerialCommand cmd) {
        return cmd.getHeader().equals(header);
    }

    @Override
    protected void doParser(SerialCommand cmd) {
//        helper.translateCmd(cmd.getData());
    }
}
