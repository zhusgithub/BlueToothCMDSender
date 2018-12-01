package com.zhushen.bluetoothcmdsender.bluetooth.CallBack;

import java.util.List;

/**
 * Created by Zhushen on 2018/2/1.
 */

public interface IScanCallBack<T> {
    void discoverDevice(T t);
    void scanTimeOut();
    void scanFinish(List<T> list);
}
