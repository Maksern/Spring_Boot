package com.example.demo.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private Long id;

    private String teamName;
    private String sportType;
    private int playerNumber;
}
