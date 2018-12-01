package com.zhushen.bluetoothcmdsender.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;

import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IPairCallback;
import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IScanCallBack;
import com.zhushen.bluetoothcmdsender.bluetooth.Receiver.BlueToothReceiver;
import com.zhushen.bluetoothcmdsender.bluetooth.Receiver.PairBroadcastReceiver;
import com.zhushen.bluetoothcmdsender.bluetooth.Receiver.ScanBroadcastReceiver;


/**
 * Created by Zhushen on 2018/2/2.
 */

public class BlueToothHelper {
    private Context context;

    private ScanBroadcastReceiver mScanBroadcastReceiver;
    private PairBroadcastReceiver mPairBroadcastReceiver;
    private BlueToothReceiver mBlueToothReceiver;

    //开启扫描
    public void startScan(Context context, IScanCallBack<BluetoothDevice> scanCallBack){
        this.context = context;
        if(mScanBroadcastReceiver == null){
            mScanBroadcastReceiver = new ScanBroadcastReceiver(scanCallBack);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(mScanBroadcastReceiver,intentFilter);
    }

    // 开始配对（普通）
    public void startPair(Context context, IPairCallback pairCallback){
        this.context = context;
        if(mPairBroadcastReceiver == null){
            mPairBroadcastReceiver = new PairBroadcastReceiver(pairCallback);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        context.registerReceiver(mPairBroadcastReceiver, intentFilter);
    }

    // 开始配对（使用默认pin）
    public void startPairWithPin(Context context, IPairCallback pairCallback){
        this.context = context;
        if(mPairBroadcastReceiver == null){
            mPairBroadcastReceiver = new PairBroadcastReceiver(pairCallback);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        context.registerReceiver(mPairBroadcastReceiver, intentFilter);
    }

    //自动配对(MAC)
    public void autoPairWithMAC(Context context, String pin, String MAC,
                                IScanCallBack<BluetoothDevice> scanCallBack,
                                IPairCallback pairCallback){
        this.context = context;
        if(mBlueToothReceiver == null){
            mBlueToothReceiver = new BlueToothReceiver(pin,MAC,scanCallBack,pairCallback);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        context.registerReceiver(mBlueToothReceiver, intentFilter);
    }


    public void cancelAll() {
        if(mScanBroadcastReceiver != null){
            context.unregisterReceiver(mScanBroadcastReceiver);
            mScanBroadcastReceiver = null;
        }
        if(mPairBroadcastReceiver != null){
            context.unregisterReceiver(mPairBroadcastReceiver);
            mPairBroadcastReceiver = null;
        }
    }
}
