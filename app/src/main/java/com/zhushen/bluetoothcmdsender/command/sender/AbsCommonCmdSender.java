package com.zhushen.bluetoothcmdsender.command.sender;

import android.util.Log;

import com.zhushen.bluetoothcmdsender.bluetooth.BlueToothManager;

import org.apache.commons.lang3.StringUtils;


/**
 * Created by Zhushen on 2018/3/12.
 */

public abstract class AbsCommonCmdSender implements ICmdSender {
    protected String header;
    protected String[] data;



    public AbsCommonCmdSender() {
    }

    public AbsCommonCmdSender(String[] data) {
        this.data = data;
    }

    public AbsCommonCmdSender(String header, String[] data) {
        this.header = header;
        this.data = data;
    }

    @Override
    public void send() {
        // TODO: 2018/3/12  send cmd
        Log.d("zhushen","send cmd = " + createCmd().toString());
        BlueToothManager.getInstance().getConnectThread().write(createCmd().toString());
        Log.d("zhushen","send cmd = " + createCmd().toString());
    }

    public StringBuilder createCmd(){
        StringBuilder cmdBuilder = new StringBuilder();

        cmdBuilder.append("FF");

        cmdBuilder.append(indicateHeader());

        data = indicateData();
        for(String item :data){
            String value  = StringUtils.trimToEmpty(item);
            cmdBuilder.append(StringUtils.leftPad(value, 2, '0'));
        }

        cmdBuilder.append("FC");

        return cmdBuilder;
    }


    protected abstract String indicateHeader();

    protected abstract String[] indicateData();
}
