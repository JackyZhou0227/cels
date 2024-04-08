package com.kclm.cels.entity;

import java.io.Serializable;

public class TestData implements Serializable {
    private int id;
    private String en;
    private String cn;
    private boolean isRight;
    private char symbol;

    public TestData(int id, String en, String cn, boolean isRight) {
        this.id = id;
        this.en = en;
        this.cn = cn;
        this.isRight = isRight;
        if (isRight){
            this.symbol = '√';
        }else if (!isRight){
            this.symbol = '〤';
        }
    }
    public TestData(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


}
