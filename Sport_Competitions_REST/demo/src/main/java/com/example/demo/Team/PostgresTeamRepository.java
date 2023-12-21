package com.example.demo.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PostgresTeamRepository implements TeamRepository {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "SELECT * FROM teams ORDER BY teamId";
    private final String FIND_BY_ID = "SELECT * FROM teams WHERE teamId=?";
    private final String UPDATE = "UPDATE teams SET teamName=?, sportType=?, playerNumber=? WHERE teamId=?";
    private final String INSERT = "INSERT INTO teams(teamName, sportType, playerNumber) VALUES(?, ?, ?);";
    private final String DELETE_BY_ID = "DELETE FROM teams WHERE teamId=?";

    private static Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();
        team.setId(rs.getLong("teamId"));
        team.setTeamName(rs.getString("teamName"));
        team.setSportType(rs.getString("sportType"));
        team.setPlayerNumber(rs.getInt("playerNumber"));
        return team;
    }

    @Override
    public Team getByID(Long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID, PostgresTeamRepository::mapRow, id); 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Iterable<Team> getAll() {
        return jdbcTemplate.query(FIND_ALL, PostgresTeamRepository::mapRow);
    }

    @Override
    public Team save(Team team) {
        if (update(team) == 1) return team;
        return create(team);
    }

    private int update(Team team) {
        return jdbcTemplate.update(UPDATE, team.getTeamName(), team.getSportType(), team.getPlayerNumber(), team.getId());
    }

    private Team create(Team team) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(INSERT, Types.VARCHAR, Types.VARCHAR, Types.INTEGER);
        pscf.setGeneratedKeysColumnNames("teamid");


        PreparedStatementCreator preparedStatementCreator = pscf.newPreparedStatementCreator(
                new Object[] {team.getTeamName(), team.getSportType(), team.getPlayerNumber()});

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        Long newId = keyHolder.getKey().longValue();

        return new Team(newId, team.getTeamName(), team.getSportType(), team.getPlayerNumber());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

}
