package com.example.demo.Team.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private Long teamid;

    private String teamName;

    private String sportType;
    private int playerNumber;

    public static TeamDTO fromEntity(Team team){
        return new TeamDTO(team.getTeamid(), team.getTeamName(), team.getSportType(), team.getPlayerNumber());
    }    
}

