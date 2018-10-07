package com.cntn16.cong.funnytest;

import java.io.Serializable;

public class FunnyTest implements Serializable{
    protected String question;
    protected String[] answer;
    protected int truePos;
    protected int type;
    public FunnyTest(String question, String[] answer, int truePos, int type){
        this.question=question;
        this.truePos=truePos;
        this.type=type;
        this.answer=answer;
    }
}
