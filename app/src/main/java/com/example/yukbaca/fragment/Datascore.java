package com.example.yukbaca.fragment;

public class Datascore {
    private int score;
    private int scorehuruf;
    private int scorebunyi;
    private int scorekata;

    public Datascore(int score, int scorehuruf, int scorebunyi, int scorekata) {
        this.score = score;
        this.scorehuruf = scorehuruf;
        this.scorebunyi = scorebunyi;
        this.scorekata = scorekata;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScorehuruf() {
        return scorehuruf;
    }

    public void setScorehuruf(int scorehuruf) {
        this.scorehuruf = scorehuruf;
    }

    public int getScorebunyi() {
        return scorebunyi;
    }

    public void setScorebunyi(int scorebunyi) {
        this.scorebunyi = scorebunyi;
    }

    public int getScorekata() {
        return scorekata;
    }

    public void setScorekata(int scorekata) {
        this.scorekata = scorekata;
    }
}
