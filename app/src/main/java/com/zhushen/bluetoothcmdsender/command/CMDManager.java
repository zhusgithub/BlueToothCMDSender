package com.zhushen.bluetoothcmdsender.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/5/2.
 */
public class CMDManager {
    private List<SommandEntity> sommandEntityList = new ArrayList<>();

    private static CMDManager instance;

    public static CMDManager getInstance() {
        if(instance == null){
            instance = new CMDManager();
        }
        return instance;
    }

    public CMDManager() {
        initCMD();
    }

    public List<SommandEntity> getSommandEntityList() {
        return sommandEntityList;
    }

    private void initCMD(){
        sommandEntityList = new ArrayList<>();

        SommandEntity a = new SommandEntity();
        a.setCommand("ff0011fc");
        a.setDetail("模拟人不论何状态，都进入复位；并退出受控状态");
        sommandEntityList.add(a);

        SommandEntity entity = new SommandEntity();
        entity.setCommand("ff00a0fc");
        entity.setDetail("间隔10秒内发送，用于指示连接正确，不需要响应和回复指令");
        sommandEntityList.add(entity);

        SommandEntity entity2 = new SommandEntity();
        entity2.setCommand("ff1010fc");
        entity2.setDetail("就绪查询。模拟人进入联机工作，并处于无脉博、瞳孔大的等待状态。");
        sommandEntityList.add(entity2);

        SommandEntity entity3 = new SommandEntity();
        entity3.setCommand("ff2020fc");
        entity3.setDetail("模拟人从等待状态→CPR工作状态;");
        sommandEntityList.add(entity3);

        SommandEntity entity4 = new SommandEntity();
        entity4.setCommand("ff3030fc");
        entity4.setDetail("完成CPR操作，模拟人瞳孔小，有脉搏（需按脉），心律为窦性；");
        sommandEntityList.add(entity4);

        SommandEntity entity5 = new SommandEntity();
        entity5.setCommand("ff4040fc");
        entity5.setDetail("未完成CPR操作，模拟人瞳孔大，无脉搏，心律为停止；");
        sommandEntityList.add(entity5);

        SommandEntity entity6 = new SommandEntity();
        entity6.setCommand("ff5050fc");
        entity6.setDetail("从CPR状态返回至等待状态。");
        sommandEntityList.add(entity6);

        //todo
        SommandEntity entity7 = new SommandEntity();
        entity7.setCommand("ff7140fc");
        entity7.setDetail("不能除颤 心搏停止");
        sommandEntityList.add(entity7);


        //todo
        SommandEntity entity8 = new SommandEntity();
        entity8.setCommand("ff7121fc");
        entity8.setDetail("能除颤/忽略  室颤 ");
        sommandEntityList.add(entity8);

        SommandEntity entityA = new SommandEntity();
        entityA.setCommand("ff7100fc");
        entityA.setDetail("救活心率(窦性)");
        sommandEntityList.add(entityA);

        SommandEntity entityB = new SommandEntity();
        entityB.setCommand("ff7160fc");
        entityB.setDetail("失败心率(死亡)");
        sommandEntityList.add(entityB);


        //todo
        SommandEntity entity9 = new SommandEntity();
        entity9.setCommand("ffa111fc");
        entity9.setDetail("按压频率及工作方式-100");
        sommandEntityList.add(entity9);

        SommandEntity entity10 = new SommandEntity();
        entity10.setCommand("ffa121fc");
        entity10.setDetail("按压频率及工作方式-110");
        sommandEntityList.add(entity10);

        SommandEntity entity11 = new SommandEntity();
        entity11.setCommand("ffa131fc");
        entity11.setDetail("按压频率及工作方式-120");
        sommandEntityList.add(entity11);

        SommandEntity entity12 = new SommandEntity();
        entity12.setCommand("ffa101fc");
        entity12.setDetail("按压频率及工作方式-无");
        sommandEntityList.add(entity12);

        List<String> details = new ArrayList<>();
        details.add("无提示");
        details.add(" ");
        details.add("按压过大");
        details.add("按压不足");
        details.add("按压位置错误");
        details.add("按压次数太少");
        details.add("释放不足");
        details.add("按压次数太多");
        details.add("按压频率不正确");
        details.add("吹气过大");
        details.add("吹气不足");
        details.add("进胃");
        details.add("气道堵塞");
        details.add("吹气次数太少");
        details.add("吹气次数太多");

        for(int i =0;i<15;i++){
            String command = "0" + Integer.toHexString(i);
            SommandEntity entity13 = new SommandEntity();
            entity13.setCommand("ffa2" + command + "fc");
            entity13.setDetail(details.get(i));
            sommandEntityList.add(entity13);
        }

        SommandEntity temp = new SommandEntity();
        temp.setCommand("ff7100fcff3030fc");
        temp.setDetail("测试先修改除颤模式，紧跟救活指令");
        sommandEntityList.add(temp);
    }

    public void addCMD(String cmd, String desc){
        SommandEntity entity = new SommandEntity();
        entity.setCommand(cmd);
        entity.setDetail(desc);
        sommandEntityList.add(entity);
    }
}
