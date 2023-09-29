package com.example.lab1;

public class Result {
    public String event;
    public String winner;
    public String score;

    public Result(String event, String winner, String score) {
        this.event = event;
        this.winner = winner;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Result{" +
                "event='" + event + '\'' +
                ", winner='" + winner + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
