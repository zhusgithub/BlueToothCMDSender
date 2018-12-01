package com.zhushen.bluetoothcmdsender.bluetooth.CallBack;

/**
 * Created by Zhushen on 2018/2/1.
 */

public interface IPairCallback {
    void unBonded();
    void bonding();
    void bonded();
    void bondFail();
}
