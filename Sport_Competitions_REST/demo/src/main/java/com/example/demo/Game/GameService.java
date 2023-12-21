package com.example.demo.Game;

import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.demo.Game.Models.Game;
import com.example.demo.Game.Models.GameDTO;
import com.example.demo.Team.Team;
import com.example.demo.Team.TeamRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public Game createGame(GameDTO gameDTO) {
        Game game = convertDtoToEntity(gameDTO);
        game = gameRepository.save(game);
        return game;
    }

    public Iterable<Game> getAll() {
        return gameRepository.getAll();
    }

    public Game getByID(Long id) {
        return gameRepository.getByID(id);
    }

    public Iterable<Game> getPage(int page, int size){
        page = Math.abs(page - 1);
        Iterable<Game> games = gameRepository.getAll();
        Iterable<Game> gameOnPage = getElementsOnPage(games, page, size);
        
        return gameOnPage;
    }

    public Iterable<Game> getBySport(String sportType){
        Iterable<Game> games = gameRepository.getAll();

        if (sportType.equals("all")) {
            return games;
        }
        
        return StreamSupport.stream(games.spliterator(), false)
                .filter(game -> game.getSportType().equals(sportType))
                .collect(Collectors.toList());
    }

    public Iterable<Game> getByTime(String sportType){
        Iterable<Game> games = gameRepository.getAll();
        
        return games;
    }

    public Game updateGame(Long id, GameDTO gameDTO) {
        Game game = convertDtoToEntity(gameDTO);
        game.setId(id);
        return gameRepository.save(game);
    }

    public void deleteGameById(Long id) {
        gameRepository.delete(id);
    }

        
    public Iterable<Game> getElementsOnPage(Iterable<Game> iterable, int page, int count) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .skip(page*count)
                .limit(count)
                .collect(Collectors.toList());
    }

    public Game convertDtoToEntity(GameDTO gameDTO) {
        Game game = new Game();
        game.setSportType(gameDTO.getSportType());
        game.setGamePlace(gameDTO.getGamePlace());
        game.setGameTime(Timestamp.valueOf(gameDTO.getGameTime()));

        Team homeTeam = teamRepository.getByID(gameDTO.getHomeTeamId());
        game.setHomeTeam(homeTeam);

        Team guestTeam = teamRepository.getByID(gameDTO.getGuestTeamId());
        game.setGuestTeam(guestTeam);

        game.setHomeTeamScore(gameDTO.getHomeTeamScore());
        game.setGuestTeamScore(gameDTO.getGuestTeamScore());
        return game;
    }
}
