package com.example.demo.Team.Models;


import java.util.Collection;

import com.example.demo.Game.Models.Game;
import com.example.demo.Validation.SportListConstraint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEAMS")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long teamid;

    @NotBlank
    @Column(name = "teamname")
    @Size(min = 5, max = 25)
    private String teamName;

    @SportListConstraint
    @Column(name = "sporttype")
    private String sportType;

    @Positive
    @Column(name = "playernumber")
    private int playerNumber;


    // @OneToMany(mappedBy = "homeTeam")
    // @ToString.Exclude
    // @EqualsAndHashCode.Exclude
    // Collection<Game> homeGames;

    // @OneToMany(mappedBy = "guestTeam")
    // @ToString.Exclude
    // @EqualsAndHashCode.Exclude
    // Collection<Game> guestGames;
}
