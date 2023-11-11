package com.example.sport_competitions.demo.Game;

import lombok.Data;

@Data
public class GameCreateDTO{
    private String sportType;
    private String time;
    private String place;

    private long guestTeamID;
    private long homeTeamID;

    private int homeTeamScore;
    private int guestTeamScore; 
}
