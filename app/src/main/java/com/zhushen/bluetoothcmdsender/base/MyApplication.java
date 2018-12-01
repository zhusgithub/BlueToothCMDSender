package com.zhushen.bluetoothcmdsender.base;

import android.app.Application;

import com.zhushen.bluetoothcmdsender.bluetooth.BlueToothManager;


/**
 * Created by Zhushen on 2018/2/26.
 */

public class MyApplication extends Application {
//    protected ModelController modelController;
//    protected CPRController cprController;
    protected BlueToothManager blueToothManager;
//    private CommandManager commandManager;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        blueToothManager = BlueToothManager.getInstance();
    }
}
