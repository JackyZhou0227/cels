package com.kclm.cels.entity;

import java.io.Serializable;


//抽象父类
public class BaseTerm implements Comparable<BaseTerm> , Serializable {

    public static final long serialVersionUID = -355111225062287520L;

    private Long id;
    private String en;

    private String[] cn;
    private String category = "计算机英语";

    public BaseTerm(String en, String[] cn) {
        if(en == null || cn == null){
            throw new RuntimeException("en或cn不允许为null");
        }
        this.en = en;
        this.cn = cn;
    }
    @Override
    public int compareTo(BaseTerm o) {

        return this.en.compareTo(o.en);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String[] getCn() {
        return cn;
    }

    public void setCn(String[] cn) {
        this.cn = cn;
    }
}
