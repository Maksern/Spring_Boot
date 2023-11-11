package com.example.sport_competitions.demo.Game;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_competitions.demo.Team.Team;
import com.example.sport_competitions.demo.Team.TeamRepository;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    public Game create(GameCreateDTO dto){
        Game game = converDTOtoEntity(dto);
        gameRepository.save(game);
        return game;
    }

    public Iterable<Game> getAll(){
        return gameRepository.getAll();
    }

    public Game getById(long id){
        return gameRepository.getByID(id);
    }

    public Game update(GameCreateDTO dto, long id){
        Game game = converDTOtoEntity(dto);
        game.setId(id);
        gameRepository.save(game);
        return game;
    }

    public Game delete(long id){
        Game game = gameRepository.getByID(id);
        gameRepository.delete(id);
        return game;
    }

    public Game converDTOtoEntity(GameCreateDTO dto){
        Team homeTeam = teamRepository.getByID(dto.getHomeTeamID());
        Team guestTeam = teamRepository.getByID(dto.getGuestTeamID());
        long id = generateUniqueGameId(gameRepository.getAll());

        Game game = new Game(id, dto.getSportType(), 
                            dto.getTime(), dto.getPlace(),
                            guestTeam, homeTeam, 
                            dto.getHomeTeamScore(), dto.getGuestTeamScore());
        return game;
    }

    private long generateUniqueGameId(Iterable<Game> games) {
        long id = IterableUtils.size(games);

        while (!isIdUnique(id, games)) {
            id++;
        }

        return id;
    }

    private boolean isIdUnique(long id, Iterable<Game> games) {
        for (Game game : games) {
            if (game.getId() == id) {
                return false;
            }
        }
        return true;
    }
}
