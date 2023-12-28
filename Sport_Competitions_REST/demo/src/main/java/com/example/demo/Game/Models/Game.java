package com.example.demo.Game.Models;

import java.sql.Timestamp;

import com.example.demo.Team.Models.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GAMES")
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long gameid;

    @Column(name = "sporttype")
    private String sportType;

    @Column(name = "gameplace")
    private String gamePlace;

    @Column(name = "gametime")
    private Timestamp gameTime;

    @ManyToOne
    @JoinColumn(name="hometeamid", referencedColumnName = "teamid")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name="guestteamid", referencedColumnName = "teamid")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Team guestTeam;

    @Column(name = "hometeamscore")
    private int homeTeamScore;
    @Column(name = "guestteamscore")
    private int guestTeamScore;
}
