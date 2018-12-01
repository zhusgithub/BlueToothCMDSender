package com.zhushen.bluetoothcmdsender.command;


import com.zhushen.bluetoothcmdsender.command.sender.AbsCommonCmdSender;

/**
 * Created by Zhushen on 2018/3/28.
 */
public class TestSender extends AbsCommonCmdSender {

    @Override
    public StringBuilder createCmd() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(header);
        return stringBuilder;
    }

    public TestSender(String cmd, Object o) {
        this.header = cmd;
    }

    @Override
    protected String indicateHeader() {
        return header;
    }

    @Override
    protected String[] indicateData() {
        return new String[0];
    }
}
