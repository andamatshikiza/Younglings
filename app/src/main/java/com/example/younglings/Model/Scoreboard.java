package com.example.younglings.Model;

public class Scoreboard {

    int score;
    String perfomance;
    String date;

    public Scoreboard() {
    }

    public Scoreboard(int score, String perfomance, String date) {
        this.score = score;
        this.perfomance = perfomance;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPerfomance() {
        return perfomance;
    }

    public void setPerfomance(String perfomance) {
        this.perfomance = perfomance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
