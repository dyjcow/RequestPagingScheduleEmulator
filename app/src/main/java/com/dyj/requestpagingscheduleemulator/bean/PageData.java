package com.dyj.requestpagingscheduleemulator.bean;

/**
 * @author ：Dyj
 * @date ：Created in 2022/7/1 21:00
 * @description：data
 * @modified By：
 * @version: 1.0
 */
public class PageData {
    private int waitData;

    private int data1;

    private int data2;

    private int data3;

    private int data4;


    public PageData(int waitData, int data1) {
        this.waitData = waitData;
        this.data1 = data1;
    }

    public PageData(int waitData, int data1, int data2) {
        this.waitData = waitData;
        this.data1 = data1;
        this.data2 = data2;
    }

    public PageData(int waitData, int data1, int data2, int data3) {
        this.waitData = waitData;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public PageData(int waitData, int data1, int data2, int data3, int data4) {
        this.waitData = waitData;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }

    public PageData() {
    }

    public int getWaitData() {
        return waitData;
    }

    public void setWaitData(int waitData) {
        this.waitData = waitData;
    }

    public int getData1() {
        return data1;
    }

    public void setData1(int data1) {
        this.data1 = data1;
    }

    public int getData2() {
        return data2;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    public int getData3() {
        return data3;
    }

    public void setData3(int data3) {
        this.data3 = data3;
    }

    public int getData4() {
        return data4;
    }

    public void setData4(int data4) {
        this.data4 = data4;
    }
}
