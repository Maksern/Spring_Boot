package com.example.demo.Game.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private String sportType;
    private String gameTime;
    private String gamePlace;

    private Long homeTeamId;
    private Long guestTeamId;

    private int homeTeamScore;
    private int guestTeamScore;
}
