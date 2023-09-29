package com.example.lab1;

public class Competition {
    public String event;
    public String location;
    public String date;

    public Competition(String event, String location, String date) {
        this.event = event;
        this.location = location;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "event='" + event + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
