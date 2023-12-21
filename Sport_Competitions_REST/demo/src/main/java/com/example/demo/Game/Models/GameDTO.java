package com.example.demo.Game.Models;


import com.example.demo.Validation.SportListConstraint;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    @SportListConstraint
    private String sportType;
    
    private String gameTime;
    private String gamePlace;

    @NotNull
    @Positive
    private Long homeTeamId;

    @NotNull
    @Positive
    private Long guestTeamId;

    private int homeTeamScore;
    private int guestTeamScore;
}
