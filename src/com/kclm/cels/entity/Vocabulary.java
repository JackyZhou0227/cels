package com.kclm.cels.entity;

public class Vocabulary extends BaseTerm {

    private static final long serialVersionUID = -1947376381172066858L;

    private String acronym;//词汇编写

    public Vocabulary(String en, String[] cn) {
        super(en, cn);
    }

    public Vocabulary(String en, String[] cn, String acronym) {
        super(en, cn);
        this.acronym = acronym;
    }
}
