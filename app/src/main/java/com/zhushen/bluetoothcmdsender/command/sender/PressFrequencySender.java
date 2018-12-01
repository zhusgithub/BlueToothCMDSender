package com.zhushen.bluetoothcmdsender.command.sender;

/**
 * Created by Zhushen on 2018/3/14.
 */

public class PressFrequencySender extends AbsCommonCmdSender {

    public PressFrequencySender(String[] data) {
        super(data);
    }

    @Override
    protected String indicateHeader() {
        return "A1";
    }

    @Override
    protected String[] indicateData() {
        return data;
    }
}
