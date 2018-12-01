package com.zhushen.bluetoothcmdsender.bluetooth.Receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IScanCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhushen on 2018/2/1.
 */

public class ScanBroadcastReceiver extends BroadcastReceiver {
    private IScanCallBack<BluetoothDevice> scanCallBack;
    private final Map<String,BluetoothDevice> mDeviceMap = new HashMap<>();

    public ScanBroadcastReceiver(IScanCallBack<BluetoothDevice> scanCallBack) {
        this.scanCallBack = scanCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(scanCallBack == null){
            return;
        }
        if(intent.getAction().equals(BluetoothDevice.ACTION_FOUND)){
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if(device == null){
                return;
            }
            if(!mDeviceMap.containsKey(device.getAddress())){
                mDeviceMap.put(device.getAddress(),device);
            }
            scanCallBack.discoverDevice(device);
        }else if(intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
            final List<BluetoothDevice> deviceList = new ArrayList<>(mDeviceMap.values());
            if(deviceList!=null&&deviceList.size()>0){
                scanCallBack.scanFinish(deviceList);
            }else {
                scanCallBack.scanTimeOut();
            }
        }
    }
}
