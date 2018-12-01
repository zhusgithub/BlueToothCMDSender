package com.zhushen.bluetoothcmdsender.bluetooth.Thread;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.medmod.simcprtest.bluetooth.utils.HexUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import static com.medmod.simcprtest.bluetooth.Constant.MessageType.MESSAGE_READ;

/**
 * Created by Zhushen on 2018/2/2.
 */

public class ConnectThread extends Thread {
    private BluetoothSocket btSocket;

    private final InputStream mInStream;
    private final OutputStream mOutStream;

    private Handler handler;

    public ConnectThread(BluetoothSocket btSocket, Handler handler) {
        this.btSocket = btSocket;
        this.handler = handler;

        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try {
            tmpIn = btSocket.getInputStream();
            tmpOut = btSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mInStream = tmpIn;
        mOutStream = tmpOut;

    }

    public void run() {
        try {
            Log.d("zhushen","in connect thread");
            byte[] data = new byte[1024];
            int len = 0;
            String result = "";

            while (len != -1) {
                if (mInStream.available() > 0 == false) {//inputStream接收的数据是一段段的
                    continue;
                } else {
                    try {
                        Thread.sleep(500);//等待0.5秒，让数据接收完整
                        len = mInStream.read(data);
//                        result = URLDecoder.decode(new String(data, "utf-8"));

                        byte[] buffer = new byte[len];
                        System.arraycopy(data, 0, buffer, 0, buffer.length);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(len);
                        bos.write(buffer,0,len);
                        result = HexUtil.encodeHexStr(buffer).toUpperCase(Locale.US);
                        Log.d("zhushen","hex utils read data = " + HexUtil.encodeHexStr(buffer));



                        if(handler!=null){
                            Message msg = new Message();
                            msg.what = MESSAGE_READ;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            mInStream.close();//关不了，也好像不能关
            Log.e("--------- :", "关闭inputStream");
            if (btSocket != null) {
                btSocket.close();
            }
        } catch (IOException e) {
            Log.e("TAG", e.toString());
        }
    }

    public void write(byte[] buffer){
        if(btSocket.isConnected()){
            try {
                mOutStream.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String command){
        if(btSocket.isConnected()){
            try {
                mOutStream.write(HexUtil.decodeHex(command.toCharArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel(){
        try {
            btSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
