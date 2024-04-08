package com.kclm.cels.entity;

import java.io.Serializable;

public class Note implements Serializable {


    private String en;
    private String cn;
    private int rightNum;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public int getRightNum() {
        return rightNum;
    }

    public void setRightNum(int rightNum) {
        this.rightNum = rightNum;
    }
}
