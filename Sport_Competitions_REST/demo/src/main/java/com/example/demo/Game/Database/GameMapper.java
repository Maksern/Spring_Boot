package com.example.demo.Game.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.demo.Game.Models.Game;
import com.example.demo.Team.Team;

@Component
public class GameMapper implements RowMapper<Game>{

    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();
        game.setId(rs.getLong("gameId"));

        game.setGamePlace(rs.getString("gamePlace"));
        game.setSportType(rs.getString("sportType"));
        game.setGameTime(rs.getTimestamp("gameTime"));

        Team homeTeam = new Team();
        homeTeam.setId(rs.getLong("homeTeamId"));
        homeTeam.setTeamName(rs.getString("homeTeamName"));
        homeTeam.setSportType(rs.getString("homeTeamSportType"));
        homeTeam.setPlayerNumber(rs.getInt("homeTeamPlayerNumber"));
        game.setHomeTeam(homeTeam);

        Team guestTeam = new Team();
        guestTeam.setId(rs.getLong("guestTeamId"));
        guestTeam.setTeamName(rs.getString("guestTeamName"));
        guestTeam.setSportType(rs.getString("guestTeamSportType"));
        guestTeam.setPlayerNumber(rs.getInt("guestTeamPlayerNumber"));
        game.setGuestTeam(guestTeam);

        game.setHomeTeamScore(rs.getInt("homeTeamScore"));
        game.setGuestTeamScore(rs.getInt("guestTeamScore"));
        return game;
    }
    
}
