package com.zhushen.bluetoothcmdsender.command.sender;

/**
 * Created by Zhushen on 2018/3/13.
 */

public class StateSender extends AbsCommonCmdSender {

    public StateSender(String header, String[] data) {
        super(header, data);
    }

    public static final StateSender exit = new StateSender("00",new String[] {"00"});


    public static final StateSender init = new StateSender("10",new String[] {"10"});


    public static final StateSender initCPR= new StateSender("20",new String[] {"20"});


    public static final StateSender CPRComplete= new StateSender("30",new String[] {"30"});


    public static final StateSender CPRFailed= new StateSender("40",new String[] {"40"});


    public static final StateSender reset= new StateSender("50",new String[] {"50"});



    @Override
    protected String indicateHeader() {
        return header;
    }

    @Override
    protected String[] indicateData() {
        return data;
    }
}
