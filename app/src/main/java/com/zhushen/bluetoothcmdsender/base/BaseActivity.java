package com.zhushen.bluetoothcmdsender.base;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.zhushen.bluetoothcmdsender.bluetooth.BlueToothManager;
import com.zhushen.bluetoothcmdsender.file.FileManager;

import java.lang.reflect.Method;

/**
 * Created by Zhushen on 2018/3/15.
 */

public class BaseActivity extends AppCompatActivity {
    protected BlueToothManager blueToothManager;
    protected BluetoothAdapter bluetoothAdapter;
    protected FileManager fileManager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        blueToothManager = BlueToothManager.getInstance();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        fileManager = FileManager.getInstance();
    }

    protected void ensureBluetoothDiscoverable() {
        if(bluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3);
            startActivity(intent);
        }
    }

    protected void closeBluetoothDiscoverable(){
        //尝试关闭蓝牙可见性
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode =BluetoothAdapter.class.getMethod("setScanMode", int.class,int.class);
            setScanMode.setAccessible(true);

            setDiscoverableTimeout.invoke(bluetoothAdapter, 1);
            setScanMode.invoke(bluetoothAdapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
