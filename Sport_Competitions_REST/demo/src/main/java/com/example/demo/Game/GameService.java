package com.example.demo.Game;

import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.demo.Game.Models.Game;
import com.example.demo.Game.Models.GameCreateDTO;
import com.example.demo.Team.TeamRepository;
import com.example.demo.Team.Models.Team;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public Game createGame(GameCreateDTO gameDTO) {
        Game game = convertDtoToEntity(gameDTO);
        game = gameRepository.save(game);
        return game;
    }

    public Iterable<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game getByID(Long id) {
        return gameRepository.findById(id).get();
    }

    public Iterable<Game> getPage(int page, int size){
        page = Math.abs(page - 1);
        Iterable<Game> games = gameRepository.findAll();
        Iterable<Game> gameOnPage = getElementsOnPage(games, page, size);
        
        return gameOnPage;
    }

    public Iterable<Game> searchGame(String sportType, String beginDate){
        Iterable<Game> games = gameRepository.findAll();
        games  = getBySport(sportType, games);
        games = getByTime(beginDate, games);

        return games;
    }


    public Game updateGame(Long id, GameCreateDTO gameDTO) {
        Game game = convertDtoToEntity(gameDTO);
        game.setGameid(id);
        return gameRepository.save(game);
    }

    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }



    public Iterable<Game> getBySport(String sportType, Iterable<Game> games){
        if (sportType.equals("all")) {
            return games;
        }

        return StreamSupport.stream(games.spliterator(), false)
                .filter(game -> game.getSportType().equals(sportType))
                .collect(Collectors.toList());
    }



    public Iterable<Game> getByTime(String beginDate, Iterable<Game> games){       
        if (beginDate.equals("all")) {
            return games;
        }

        Timestamp timestamp = Timestamp.valueOf(beginDate.concat(" 00:00:00"));
        System.out.println(timestamp);

        return StreamSupport.stream(games.spliterator(), false)
                .filter(game -> game.getGameTime().after(timestamp))
                .collect(Collectors.toList());
    }


        
    public Iterable<Game> getElementsOnPage(Iterable<Game> iterable, int page, int count) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .skip(page*count)
                .limit(count)
                .collect(Collectors.toList());
    }

    public Game convertDtoToEntity(GameCreateDTO gameDTO) {
        Game game = new Game();
        game.setSportType(gameDTO.getSportType());
        game.setGamePlace(gameDTO.getGamePlace());
        game.setGameTime(Timestamp.valueOf(gameDTO.getGameTime()));

        Team homeTeam = teamRepository.findById(gameDTO.getHomeTeamId()).get();
        game.setHomeTeam(homeTeam);

        Team guestTeam = teamRepository.findById(gameDTO.getGuestTeamId()).get();
        game.setGuestTeam(guestTeam);

        game.setHomeTeamScore(gameDTO.getHomeTeamScore());
        game.setGuestTeamScore(gameDTO.getGuestTeamScore());
        return game;
    }
}
