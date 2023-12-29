package com.example.demo.Game;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Game.Models.Game;
import com.example.demo.Game.Models.GameCreateDTO;
import com.example.demo.Game.Models.GameJpaDTO;
import com.example.demo.Team.TeamRepository;
import com.example.demo.Team.Models.Team;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public GameJpaDTO createGame(GameCreateDTO gameDTO) {
        Game game = convertDtoToEntity(gameDTO);
        game = gameRepository.save(game);
        return GameJpaDTO.fromEntity(game);
    }

    @Transactional
    public Iterable<GameJpaDTO> createGames(GameCreateDTO[] gameDTOs) {
        List<GameJpaDTO> games = new ArrayList<GameJpaDTO>();
        for (GameCreateDTO gameCreateDTO : gameDTOs) {
                Game game = convertDtoToEntity(gameCreateDTO);
                game = gameRepository.save(game);
                games.add(GameJpaDTO.fromEntity(game));
        }
        return games;
    }

    public Iterable<GameJpaDTO> getAll() {
        Iterable<Game> games = gameRepository.findAll(Sort.by("gameid"));
        Iterable<GameJpaDTO> gamesDtos = StreamSupport.stream(games.spliterator(), false)
                                            .map(GameJpaDTO::fromEntity)
                                            .collect(Collectors.toList());

        return gamesDtos;
    }

    public GameJpaDTO getByID(Long id) {
        return gameRepository.findById(id).map(GameJpaDTO::fromEntity).get();
    }

    public Iterable<GameJpaDTO> getPage(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Iterable<Game> games = gameRepository.findAll(pageable);

        Iterable<GameJpaDTO> gamesDtos = StreamSupport.stream(games.spliterator(), false)
                                    .map(GameJpaDTO::fromEntity)
                                    .collect(Collectors.toList());
        
        return gamesDtos;
    }


    public GameJpaDTO updateGame(Long id, GameCreateDTO gameDTO) {
        Game game = convertDtoToEntity(gameDTO);
        game.setGameid(id);
        game = gameRepository.save(game);

        return GameJpaDTO.fromEntity(game);
    }

    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }



    public Iterable<GameJpaDTO> getBySport(String sportType){
        Iterable<Game> games = gameRepository.findBySportType(sportType);

        Iterable<GameJpaDTO> gamesDtos = StreamSupport.stream(games.spliterator(), false)
                                    .map(GameJpaDTO::fromEntity)
                                    .collect(Collectors.toList());
        return gamesDtos;
    }



    public Iterable<GameJpaDTO> getByTime(String beginDate){       
        Timestamp timestamp = Timestamp.valueOf(beginDate.concat(" 00:00:00"));
        Iterable<Game> games = gameRepository.findByGameTimeAfter(timestamp);

        Iterable<GameJpaDTO> gamesDtos = StreamSupport.stream(games.spliterator(), false)
                                    .map(GameJpaDTO::fromEntity)
                                    .collect(Collectors.toList());
        return gamesDtos;
    }


    private Game convertDtoToEntity(GameCreateDTO gameDTO) {
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
