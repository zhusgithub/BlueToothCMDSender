package com.zhushen.bluetoothcmdsender.command;

/**
 * Created by Zhushen on 2018/2/25.
 */

public interface ICommandReceived {
    void onCommand(String[] cmd);
}
