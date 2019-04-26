package com.utils;

/**
 * Created by qianghe on 2019/4/12.
 */
//****************************************************************************
//函数名称:		CRC16

//功能说明:		CRC校验码生成程序

//输入：
//	puchMsg ： 	用于计算 CRC 的报文

//返回：
//	int[] 类型 CRC

// 备注  :
// ****************************************************************************/
public class CRC16 {
    public static int[] getCrc16(byte[] data) {

        int flag;
        //16位寄存器，所有位置均为1
        int wcrc = 0xffff;
        for(int i = 0; i < data.length; i++){
            //16位寄存器的地位字节
            //取被校验串的一个字节与16位寄存器的低位字节进行“异或（xor）”运算
            wcrc = wcrc ^ data[i];
            for(int j = 0; j < 8; j++){
                flag = wcrc & 0x0001;
                //右移动1位
                wcrc = wcrc >> 1;
                if(flag == 1)
                    wcrc ^= 0xa001;
            }
        }
        //获取低8位
        //修改后为高八位
        int up = wcrc >> 8;//或wcrc / 256；
        //获取高8位
        //修改后为低八位
        int low = wcrc % 256;

        int[] crc = {up,low};//10进制
        return crc;


   }

    /*private static byte[] getCrc16(byte[] arr_buff) {
        int len = arr_buff.length;

        // 预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
        int crc = 0xFFFF;
        int i, j;
        for (i = 0; i < len; i++) {
            // 把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arr_buff[i] & 0xFF));
            for (j = 0; j < 8; j++) {
                // 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
                if ((crc & 0x0001) > 0) {
                    // 如果移出位为 1, CRC寄存器与多项式A001进行异或
                    crc = crc >> 1;
                    crc = crc ^ 0xA001;
                } else
                    // 如果移出位为 0,再次右移一位
                    crc = crc >> 1;
            }
        }
        return intToBytes(crc);

    }
    *//**
     * 将int转换成byte数组，低位在前，高位在后
     * 改变高低位顺序只需调换数组序号
     *//*
    private static byte[] intToBytes(int value)  {
        byte[] src = new byte[2];
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }
    *//**
     * 将字节数组转换成十六进制字符串
     *//*
    *//*public static String byteTo16String(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : data) {
            buffer.append(byteTo16String(b));
        }
        return buffer.toString();
    }
    *//**//**
     * 将字节转换成十六进制字符串
     * int转byte对照表
     * [128,255],0,[1,128)
     * [-128,-1],0,[1,128)
     *//**//*
    public static String byteTo16String(byte b) {
        StringBuffer buffer = new StringBuffer();
        int aa = (int)b;
        if (aa<0) {
            buffer.append(Integer.toString(aa+256, 16)+" ");
        }else if (aa==0) {
            buffer.append("00 ");
        }else if (aa>0 && aa<=15) {
            buffer.append("0"+Integer.toString(aa, 16)+" ");
        }else if (aa>15) {
            buffer.append(Integer.toString(aa, 16)+" ");
        }
        return buffer.toString();
    }*/
}

