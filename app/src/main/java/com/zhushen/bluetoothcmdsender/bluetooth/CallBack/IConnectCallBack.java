package com.zhushen.bluetoothcmdsender.bluetooth.CallBack;


import com.zhushen.bluetoothcmdsender.bluetooth.Constant.State;

/**
 * Created by Zhushen on 2018/2/1.
 */

public interface IConnectCallBack<T> {
    void connectStateChange(State state);
    void writeData(T data, int type);
    void readData(T data, int type);
    void setDeviceName(String name);
    void showMessage(String message, int code);
}
