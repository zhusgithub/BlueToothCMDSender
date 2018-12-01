package com.zhushen.bluetoothcmdsender.command.parser;

import android.util.Log;

//import com.medmod.simcprtest.CPR.DataHelper.CPRDataHelper;

/**
 * Created by Zhushen on 2018/3/15.
 */

public class CPRParser extends CmdParser {
//    CPRDataHelper helper;

//    public CPRParser(CPRDataHelper helper) {
//        this.helper = helper;
//    }

    @Override
    protected boolean needParser(SerialCommand cmd) {
        Log.d("zhushen","CPR need parser");
        return true;
    }

    @Override
    protected void doParser(SerialCommand cmd) {
//        helper.translateCmd(cmd.getData());
    }
}
