package com.zhushen.bluetoothcmdsender.command.parser;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/3/14.
 */

public class SerialCommand {
    public static final String START = "FF";
    public static final String END = "FC";

    private boolean broken;
    private int dataLength;

    private String header;
    private String[] data;

    public SerialCommand(String cmd) {
        String regularCmd = cmd.replaceAll(" ","");

        if(!regularCmd.endsWith(END)){
            regularCmd = repairCmd(regularCmd);
            Log.e("serialcommand-broken", regularCmd);
            Log.w("serialcommand-repaired", regularCmd);
        }
        if(broken){
            return;
        }

        parseData(regularCmd);
        Log.d("zhushen","init serial command");
    }

    private String repairCmd(String cmd) {
        setBroken(true);
        int expectedLength = (cmd.length() + 1 ) / 2;
        StringBuilder builder = new StringBuilder(cmd);
//        for(int i = )

        return builder.toString();
    }

    private void parseData(String cmd){
        List<String> data = new ArrayList<>();
        dataLength = cmd.length()/2;
//        int start  = START.length();
        int start = 0;
        for(int i = 0;i<dataLength;i++){
            int index = start + 2 * i;
            data.add(cmd.substring(index,index + 2));
        }
        setData(data.toArray(new String[data.size()]));
    }


    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
