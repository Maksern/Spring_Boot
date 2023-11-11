package com.example.sport_competitions.demo.Game;

import com.example.sport_competitions.demo.Team.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game{
    private long id;

    private String sportType;
    private String time;
    private String place;

    private Team guestTeam;
    private Team homeTeam;

    private int homeTeamScore;
    private int guestTeamScore; 
}