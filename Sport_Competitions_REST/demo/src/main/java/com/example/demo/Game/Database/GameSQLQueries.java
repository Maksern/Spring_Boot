package com.example.demo.Game.Database;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class GameSQLQueries {
    private final String BASIC_QUERY = "SELECT gameid, games.sporttype, gamePlace, gameTime, \r\n" + //
                                        "\thomeTeamId, homeTeam.teamname as homeTeamName, homeTeam.sportType as homeTeamSportType, homeTeam.playerNumber as homeTeamPlayerNumber, homeTeamScore, \r\n"+ //
                                        "\tguestTeamId, guestTeam.teamname as guestTeamName, guestTeam.sportType as guestTeamSportType, guestTeam.playerNumber as guestTeamPlayerNumber, guestTeamScore\r\n"+ //
                                    "FROM games \r\n" + //
                                        "\tINNER JOIN teams as homeTeam ON games.homeTeamId = homeTeam.teamId \r\n" + //
                                        "\tINNER JOIN teams as guestTeam ON games.guestTeamId = guestTeam.teamId \r\n ";
    private final String FIND_ALL = BASIC_QUERY + "ORDER BY gameId";
    private final String FIND_BY_ID = BASIC_QUERY + "where gameId=?";
    private final String UPDATE = "UPDATE games SET sporttype=?, gameplace=?, gametime=?, hometeamid=?, guestteamid=?, hometeamscore=?, guestteamscore=? WHERE gameId=?";
    private final String CREATE = "INSERT INTO games(sporttype, gameplace, gametime, hometeamid, guestteamid, hometeamscore, guestteamscore) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE = "DELETE FROM games WHERE gameId=?";
}
