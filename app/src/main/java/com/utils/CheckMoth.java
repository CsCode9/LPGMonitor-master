package com.utils;

public class CheckMoth {
    public static int getCheck(byte[] data) {
        int temp = 0x0000;
        for (int i = 0; i < data.length; i++){
            temp = temp ^ data[i];
        }
        if (temp < 0){
            temp = temp + 256;
        }else {

        }
        return temp;
        /*int temp = data[1];              // 此处首位取1是因为本协议中第一个数据不参数异或校验，转为int防止结果出现溢出变成负数

        for (int i = 2; i < data.length; i++) {
            int preTemp = temp;
            int iData;
            if (data[i] < 0) {
                iData = data[i] & 0xff;      // 变为正数计算
            } else {
                iData = data[i];
            }
            if (temp < 0) {
                temp = temp & 0xff;          // 变为正数
            }
            temp ^= iData;

        }
        return temp;*/
    }
    /*public static byte getCheck(byte[] datas){

        byte temp=datas[0];

        for (int i = 1; i <datas.length; i++) {
            temp ^=datas[i];
        }

        return temp;
    }*/

}
