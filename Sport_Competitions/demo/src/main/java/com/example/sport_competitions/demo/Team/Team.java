package com.example.sport_competitions.demo.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private long id;

    private String sportType;
    private String name;
    private int playerNumber;
}
