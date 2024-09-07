package com.kclm.cels.entity;

/********************
 * 单词类
 */

public class Word extends BaseTerm {

    private static final long serialVersionUID = 2353275780690096682L;

    private char first;

    public Word(long id, String en, String[] cn) {
        super(en, cn);
        this.setId(id);
        this.first = en.toUpperCase().charAt(0);
    }

    public Word(String en, String[] cn) {
        super(en, cn);
        this.first = en.toUpperCase().charAt(0);
    }

    public char getFirst() {
        return first;
    }

    public void setFirst(char first) {
        this.first = first;
    }

}
