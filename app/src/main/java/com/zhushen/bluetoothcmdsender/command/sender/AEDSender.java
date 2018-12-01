package com.zhushen.bluetoothcmdsender.command.sender;

/**
 * Created by Zhushen on 2018/3/17.
 */

public class AEDSender extends AbsCommonCmdSender {
    @Override
    protected String indicateHeader() {
        return "71";
    }

    @Override
    protected String[] indicateData() {
        return data;
    }
}
