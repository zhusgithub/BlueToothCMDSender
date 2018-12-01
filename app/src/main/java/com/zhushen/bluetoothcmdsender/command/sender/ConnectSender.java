package com.zhushen.bluetoothcmdsender.command.sender;

/**
 * Created by Zhushen on 2018/3/14.
 * 模拟人不论何状态，都进入复位；发送模拟人标识码，并进入受控状态（0x11）/非受控状态（0x01）
 * 注：冲突时，标识码都为0，状态不改变；
 * 0xA0：间隔10秒内发送，用于指示连接正确，不需要响应和回复指令；
 */

public class ConnectSender extends AbsCommonCmdSender {

    @Override
    protected String indicateHeader() {
        return "00";
    }

    @Override
    protected String[] indicateData() {
        return new String[] {"A0"};
    }
}
