package com.zhushen.bluetoothcmdsender.file;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/12/1.
 */
public class FileManager {
    private static FileManager instance;

    private String filePath;
    private List<String> fileNameList;
    private List<File> fileList;

    private File curFile;
    private File[] files;

    public static FileManager getInstance(){
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }

    public FileManager() {
        filePath = "/sdcard/BlueToothCMDSender";
        fileNameList = new ArrayList<>();
        fileList = new ArrayList<>();
        curFile = new File(filePath);
        if (!curFile.exists()) {
            try {
                curFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fileList.add(curFile);
        files = curFile.listFiles();
    }

    private void createFile(String fileName){
        File file = new File(filePath  + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createText(String textName){
        File file = new File(filePath  + textName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectFile(String fileName){
        fileNameList.add(fileName);
    }

    private void back(){
        if(fileNameList.size()>0){
            fileNameList.remove(fileNameList.size()-1);
        }

//        curFile =
    }

    private void selectText(String textName){

    }

    private void deleteFile(String fileName){

    }

    private void deleteText(){

    }


    private List<String> readText(){
        List newList =new ArrayList<String>();
        try {
            File file = new File(filePath);
            int count = 0;//初始化 key值
            if (file.isFile() && file.exists()) {//文件存在
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    if (!"".equals(lineTxt)) {
                        String reds = lineTxt.split("\\+")[0];  //java 正则表达式
                        newList.add(count, reds);
                        count++;
                    }
                }
                isr.close();
                br.close();
            }else {
                //can not find file
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;

    }

}
