package com.zhushen.bluetoothcmdsender.command;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import com.zhushen.bluetoothcmdsender.command.parser.CmdParserHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/2/24.
 */

public class CommandManager {
    private static CommandManager instance;


    //指令执行线程
    private Thread CmdExecuteThread = null;

    private Handler CmdExceuteHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("zhushen","handler get message");
            String cmd = (String) msg.obj;
            CmdParserHolder.parse(cmd);
        }
    };

    public static CommandManager getInstance(){
        if(instance == null){
            instance = new CommandManager();
        }
        return instance;
    }

    private List<ICommandReceived> iCommandReceivers;

    public CommandManager() {
        iCommandReceivers = new ArrayList<>();
    }

    public void registerCommandReceivedListener(ICommandReceived listener){
        if(!iCommandReceivers.contains(listener)){
            iCommandReceivers.add(listener);
        }
    }

    public void removeCommandListener(ICommandReceived listener){
        if(iCommandReceivers.contains(listener)){
            iCommandReceivers.remove(listener);
        }}

    private void onCommadReceived(String[] cmd){
        for (ICommandReceived listener:iCommandReceivers) {
            listener.onCommand(cmd);
        }
    }



    private void startCmdExecuteThread(){
        CmdExecuteThread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (true){

                }
            }
        });
    }


    public Handler getCmdExceuteHandler() {
        return CmdExceuteHandler;
    }
}
