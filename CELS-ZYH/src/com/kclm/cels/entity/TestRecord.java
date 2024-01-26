package com.kclm.cels.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class TestRecord implements Serializable {

    private LocalDateTime time;
    private long totalTime;
    private int totalNum;
    private int doNum;
    private int rightNum;
    private int wrongNum;

    private List<TestData> dataList;

    public void printRecord(){
        System.out.println("-------------------------------------------------------------------");
        System.out.println("测试时间：" + this.time);
        System.out.println("测试时长：" + this.totalTime + "秒");
        System.out.println("测数总题数：" + this.totalNum);
        System.out.println("完成题数：" + this.doNum);
        System.out.println("正确个数：" + this.rightNum);
        System.out.println("错误个数：" + this.wrongNum);
        for (TestData testData : this.dataList) {
            System.out.println(testData.getId()+". "+testData.getEn()+" [" + testData.getCn()+"] "+testData.getSymbol());
        }
        System.out.println("-------------------------------------------------------------------");
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getDoNum() {
        return doNum;
    }

    public void setDoNum(int doNum) {
        this.doNum = doNum;
    }

    public int getRightNum() {
        return rightNum;
    }

    public void setRightNum(int rightNum) {
        this.rightNum = rightNum;
    }

    public int getWrongNum() {
        return wrongNum;
    }

    public void setWrongNum(int wrongNum) {
        this.wrongNum = wrongNum;
    }

    public List<TestData> getDataList() {
        return dataList;
    }

    public void setDataList(List<TestData> dataList) {
        this.dataList = dataList;
    }


}
