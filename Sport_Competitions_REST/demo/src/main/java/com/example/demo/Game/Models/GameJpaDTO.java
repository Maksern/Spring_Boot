package com.example.demo.Game.Models;



import java.sql.Timestamp;

import com.example.demo.Team.Models.TeamDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameJpaDTO {
    private Long gameid;

    private String sportType;
    private Timestamp gameTime;
    private String gamePlace;

    private TeamDTO homeTeam;
    private TeamDTO guestTeam;

    private int homeTeamScore;
    private int guestTeamScore;

    public static GameJpaDTO fromEntity(Game game){
        return new GameJpaDTO(game.getGameid(), game.getSportType(), 
                            game.getGameTime(), game.getGamePlace(), 
                            TeamDTO.fromEntity(game.getHomeTeam()), TeamDTO.fromEntity(game.getGuestTeam()),
                            game.getHomeTeamScore(), game.getGuestTeamScore());
    }

}