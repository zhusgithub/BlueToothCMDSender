package com.zhushen.bluetoothcmdsender.command.sender;

/**
 * Created by Zhushen on 2018/3/17.
 * 按压频率及工作方式
 */

public class ModeSender extends AbsCommonCmdSender {


    @Override
    protected String indicateHeader() {
        return "A1";
    }

    @Override
    protected String[] indicateData() {
        return data;
    }
}
