package com.zhushen.bluetoothcmdsender.command.receiver;

/**
 * Created by Zhushen on 2018/2/24.
 */

public class test {
    //FF,00,□,□,□,FC        发送模拟人标识码(3位)，并进入复位状态
    //FF,03,11,FC               就绪查询指令应答

    //FF,04,□,FC                仰头（0x21）、平躺（0x20）；脉搏检查：按脉（0x11）、移开（0x10）；意识判别（0x41）；清除异物（0x51）；气道未开放：未开放（0x61）、移除（0x60）
    //FF,08,□,FC                AED操作信息。

    //FF,91,□,□,FC            按压、吹气动作启停指令；
    //FF,2A,□,□,□□,FC       按压幅度值、释放/位置、时间戳
    //FF,2B, □,□,□□,FC      按压频率
    //FF,4A,□,□,□□,FC       吹气量、进胃、时间戳
    //FF,4B, □,□,□□,FC      吹气频率及通气时间

    //FF,05,□,FC                上传模拟人中电池状态。


}
