package com.zhushen.bluetoothcmdsender.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.util.Log;


import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IPairCallback;
import com.zhushen.bluetoothcmdsender.bluetooth.CallBack.IScanCallBack;
import com.zhushen.bluetoothcmdsender.bluetooth.Constant.DeviceMessage;
import com.zhushen.bluetoothcmdsender.bluetooth.Thread.ConnectThread;
import com.zhushen.bluetoothcmdsender.bluetooth.utils.ClsUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Zhushen on 2018/2/1.
 */

public class BlueToothManager {
    private static BlueToothManager instance;
    private static BlueToothHelper blueToothHelper;
    private Handler handler;
    private BluetoothDevice curDevice;

    private ConnectThread connectThread;
    private Handler connectHandler;

    public static BlueToothManager getInstance() {
        if(instance == null){
            instance = new BlueToothManager();
            blueToothHelper = new BlueToothHelper();
        }
        return instance;
    }



    public void startScan(Context context){
        blueToothHelper.startScan(context,scanCallBack);
        BluetoothAdapter.getDefaultAdapter().startDiscovery();
    }

    public void startScan(Context context, Handler handler){
        this.handler = handler;
        blueToothHelper.startScan(context,scanCallBack);
        BluetoothAdapter.getDefaultAdapter().startDiscovery();
        Log.d("zhushen","start scan ");
    }

    public void startPair(Context context, BluetoothDevice device){
        blueToothHelper.startPair(context,pairCallback);
        curDevice = device;
        try {
            ClsUtils.createBond(device.getClass(), device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
    }

    public void autoPairByMac(Context context, String MAC, Handler handler){
        BluetoothAdapter.getDefaultAdapter().startDiscovery();
        this.handler = handler;
        blueToothHelper.autoPairWithMAC(context,"1234",MAC,scanCallBack,pairCallback);
    }

    //串口模式创建蓝牙连接
    public void startCommConnect(BluetoothDevice device, Handler handler){
        this.handler = handler;
        BluetoothSocket btSocket;
        try {
            Method clientMethod = device.getClass()
                    .getMethod("createRfcommSocket", new Class[]{int.class});
            btSocket = (BluetoothSocket) clientMethod.invoke(device, 1);
            Log.e("zhushen", "startConnect and device name = " + device.getName());
            connect(btSocket);//连接设备
            Log.e("zhushen", "startConnect");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void startConnect(BluetoothDevice device){
        BluetoothSocket btSocket;
        try {
            btSocket = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            connect(btSocket);//连接设备
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnect(){
        connectThread.cancel();
        connectThread = null;
    }

    //socket创建成功后通用连接方法
    private void connect(final BluetoothSocket btSocket){
        try {
            Log.e("zhushen", "in connect function");
            btSocket.connect();//连接
            Log.e("zhushen", "after connect function");
            if (btSocket.isConnected()) {
                Log.e("zhushen", "连接成功");
                handler.obtainMessage(100).sendToTarget();
                connectThread = new ConnectThread(btSocket,connectHandler);
                connectThread.start();
            } else {
                btSocket.close();
                Log.e("zhushen", "连接关闭");
                handler.obtainMessage(101).sendToTarget();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void cancelDiscovery(){
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
    }

    public void cancelAll() {
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        handler = null;
        blueToothHelper.cancelAll();
    }

    public List<BluetoothDevice> getBondedDevices() {
        List<BluetoothDevice> data = new ArrayList<>();
        Set<BluetoothDevice> devices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        for (BluetoothDevice device :devices) {
            data.add(device);
        }
        return data;
    }

    public BluetoothDevice getCurDevice(){
        return curDevice;
    }

    public void setCurDevice(BluetoothDevice curDevice) {
        this.curDevice = curDevice;
    }

    private final IScanCallBack<BluetoothDevice> scanCallBack = new IScanCallBack<BluetoothDevice>() {
        @Override
        public void discoverDevice(BluetoothDevice bluetoothDevice) {
            if(handler != null){
                handler.obtainMessage(DeviceMessage.DEVICE_FOUNDED,bluetoothDevice).sendToTarget();
            }
        }

        @Override
        public void scanTimeOut() {
            if(handler !=null){
                handler.obtainMessage(DeviceMessage.DEVICE_SCAN_TIMEOUT).sendToTarget();
            }
        }

        @Override
        public void scanFinish(List<BluetoothDevice> list) {
            if(handler !=null){
                handler.obtainMessage(DeviceMessage.DEVICE_SCAN_FINISHED).sendToTarget();
            }
        }
    };

    private final IPairCallback pairCallback = new IPairCallback() {
        @Override
        public void unBonded() {
            //未配对
            if(handler!=null){
                handler.obtainMessage(DeviceMessage.DEVICE_PAIR_UNBONDED).sendToTarget();
            }
        }

        @Override
        public void bonding() {
            //配对中
            if(handler!=null){
                handler.obtainMessage(DeviceMessage.DEVICE_PAIR_BONDING).sendToTarget();
            }
        }

        @Override
        public void bonded() {
            //已配对
            if(handler!=null){
                handler.obtainMessage(DeviceMessage.DEVICE_PAIR_BONDED).sendToTarget();
            }
            startCommConnect(curDevice,handler);
        }

        @Override
        public void bondFail() {
            if(handler!=null){
                handler.obtainMessage(DeviceMessage.DEVICE_PAIR_FAILED).sendToTarget();
            }
        }
    };


    public void setConnectHandler(Handler connectHandler) {
        this.connectHandler = connectHandler;
        if(connectThread!=null){
            connectThread.setHandler(connectHandler);
        }
    }

    public ConnectThread getConnectThread() {
        return connectThread;
    }
}
