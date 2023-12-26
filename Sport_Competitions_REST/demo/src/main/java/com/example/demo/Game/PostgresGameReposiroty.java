package com.example.demo.Game;


import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.Game.Database.GameMapper;
import com.example.demo.Game.Database.GameSQLQueries;
import com.example.demo.Game.Models.Game;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PostgresGameReposiroty implements GameRepository {

    private final JdbcTemplate jdbcTemplate;

    private GameSQLQueries SQL = new GameSQLQueries();
    private GameMapper ROW_MAPPER = new GameMapper();


    @Override
    public Game getByID(long id) {
        return jdbcTemplate.queryForObject(SQL.getFIND_BY_ID(), ROW_MAPPER, id);
    }

    @Override
    public Iterable<Game> getAll() {
        return jdbcTemplate.query(SQL.getFIND_ALL(), ROW_MAPPER);
    }

    @Override
    public Game save(Game game) {
        if(update(game) == 1) return game;
        return create(game);
    }

    public int update(Game game){
        return jdbcTemplate.update(SQL.getUPDATE(), game.getSportType(), game.getGamePlace(), game.getGameTime(), game.getHomeTeam().getId(), game.getGuestTeam().getId(), game.getHomeTeamScore(), game.getGuestTeamScore(), game.getId());
    }

    public Game create(Game game){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(SQL.getCREATE(), Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, 
                                                                                    Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER);
        pscf.setGeneratedKeysColumnNames("gameid");

        PreparedStatementCreator pCreator = pscf.newPreparedStatementCreator(new Object[]{
            game.getSportType(), game.getGamePlace(), game.getGameTime(), game.getHomeTeam().getId(), game.getGuestTeam().getId(), game.getHomeTeamScore(), game.getGuestTeamScore()
        });

        jdbcTemplate.update(pCreator, keyHolder);

        Long newId = keyHolder.getKey().longValue();

        return new Game(newId, game.getSportType(), game.getGameTime(), game.getGamePlace(), game.getHomeTeam(), game.getGuestTeam(), game.getHomeTeamScore(), game.getGuestTeamScore());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(SQL.getDELETE(), id);
    }
    
}
