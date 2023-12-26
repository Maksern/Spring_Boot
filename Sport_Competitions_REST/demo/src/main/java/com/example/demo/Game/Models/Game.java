package com.example.demo.Game.Models;

import java.sql.Timestamp;

import com.example.demo.Team.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Long id;

    private String sportType;
    private Timestamp gameTime;
    private String gamePlace;

    private Team homeTeam;
    private Team guestTeam;

    private int homeTeamScore;
    private int guestTeamScore;
}
