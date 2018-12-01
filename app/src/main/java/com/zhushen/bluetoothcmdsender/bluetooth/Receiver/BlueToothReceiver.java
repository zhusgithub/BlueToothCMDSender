package com.zhushen.bluetoothcmdsender.bluetooth.Receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IPairCallback;
import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IScanCallBack;
import com.zhushen.bluetoothcmdsender.bluetooth.utils.ClsUtils;


/**
 * Created by Zhushen on 2018/2/1.
 */

public class BlueToothReceiver extends BroadcastReceiver {
    private String pin = "1234";
    private String deviceName = "";
    private String MAC = "";

    private IScanCallBack<BluetoothDevice> scanCallBack;
    private IPairCallback pairCallback;

    public BlueToothReceiver(String pin, String MAC,
                             IScanCallBack<BluetoothDevice> scanCallBack,
                             IPairCallback pairCallback) {
        this.pin = pin;
        this.MAC = MAC;
        this.scanCallBack = scanCallBack;
        this.pairCallback = pairCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        if(BluetoothDevice.ACTION_FOUND.equals(action)){
            if(device.getAddress().contains(MAC)){
                try {
                    scanCallBack.discoverDevice(device);
                    ClsUtils.createBond(device.getClass(), device);
                    pairCallback.bonding();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(action.equals("android.bluetooth.device.action.PAIRING_REQUEST")){
            if(device.getAddress().contains(MAC)){
                try {
                    abortBroadcast();
                    ClsUtils.setPin(device.getClass(),device,pin);
                    pairCallback.bonded();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
