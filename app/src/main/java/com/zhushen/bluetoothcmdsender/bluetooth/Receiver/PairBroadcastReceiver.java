package com.zhushen.bluetoothcmdsender.bluetooth.Receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IPairCallback;
import com.zhushen.bluetoothcmdsender.bluetooth.utils.ClsUtils;


/**
 * Created by Zhushen on 2018/2/1.
 */

public class PairBroadcastReceiver extends BroadcastReceiver {
    private IPairCallback pairCallback;
    private String pin = "1234";

    public PairBroadcastReceiver(IPairCallback pairCallback) {
        this.pairCallback = pairCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        BluetoothDevice btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        if(intent.getAction().equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if(device!=null){
                resolveBondingState(device.getBondState());
            }
        }else if(intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST")){
            abortBroadcast();//如果没有将广播终止，则会出现一个一闪而过的配对框。
            //调用setPin方法进行配对...
            try {
                boolean result = ClsUtils.setPin(btDevice.getClass(), btDevice, pin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void resolveBondingState(final int bondState){
        if (pairCallback == null) {
            return;
        }
        switch (bondState) {
            case BluetoothDevice.BOND_BONDED://已配对
                pairCallback.bonded();
                break;
            case BluetoothDevice.BOND_BONDING://配对中
                pairCallback.bonding();
                break;
            case BluetoothDevice.BOND_NONE://未配对
                pairCallback.unBonded();
                break;
            default:
                pairCallback.bondFail();
                break;
        }

    }
}
