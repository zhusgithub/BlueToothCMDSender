package com.zhushen.bluetoothcmdsender.command.parser;

import android.util.Log;

/**
 * Created by Zhushen on 2018/3/14.
 */

public abstract class CmdParser {

    private CmdParser nextParser;

    public synchronized void parser(SerialCommand cmd) {
        Log.d("zhushen","parser command");
        if(cmd.isBroken()){
            Log.d("zhushen"," command is broken ");
            return;
        }

        if(needParser(cmd)){
            doParser(cmd);
            return;
        }

        if(nextParser != null){
            nextParser.parser(cmd);
        }
    }


    protected boolean needParser(SerialCommand cmd){
        return false;
    }

    protected void doParser(SerialCommand cmd){

    }

    public CmdParser getNextParser() {
        return nextParser;
    }

    public void setNextParser(CmdParser nextParser) {
        this.nextParser = nextParser;
    }


}
