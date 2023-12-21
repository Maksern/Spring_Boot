package com.example.demo.Team;

import com.example.demo.Validation.SportListConstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private Long id;

    @NotBlank
    @Size(min = 5, max = 25)
    private String teamName;

    @SportListConstraint
    private String sportType;

    @Positive
    private int playerNumber;
}
