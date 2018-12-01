package com.zhushen.bluetoothcmdsender.bluetooth.Constant;

/**
 * Created by Zhushen on 2018/2/5.
 */

public class DeviceMessage {
    //scan
    public static final int DEVICE_FOUNDED = 1;
    public static final int DEVICE_SCAN_FINISHED = 2;
    public static final int DEVICE_SCAN_TIMEOUT = 3;

    //pair
    public static final int DEVICE_PAIR_UNBONDED = 4;
    public static final int DEVICE_PAIR_BONDING = 5;
    public static final int DEVICE_PAIR_BONDED = 6;
    public static final int DEVICE_PAIR_FAILED = 7;


}
