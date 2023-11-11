package com.example.sport_competitions.demo.Game;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import com.example.sport_competitions.demo.Team.TeamRepository;

@Repository
public class InMemoryGameRepository implements GameRepository{

    private TeamRepository teamRepository;
    Map<Long, Game> games = new TreeMap<>(); 

    @Override
    public Game getByID(long id) {
        return games.get(id);
    }

    @Override
    public Iterable<Game> getAll() {
        return games.values();
    }

    @Override
    public Game save(Game game) {
        games.put(game.getId(), game);
        return game;
    }

    @Override
    public void delete(long id) {
        games.remove(id);
    }

    public InMemoryGameRepository(TeamRepository repository){
        this.teamRepository = repository;

        games.put(0L, new Game(0L, "Football", "2023-11-02T11:00AM", "Magic Street", teamRepository.getByID(0), teamRepository.getByID(2), 5, 2));
        games.put(1L, new Game(1L, "Basketball", "2023-11-03T02:30PM", "Sports Arena", teamRepository.getByID(3), teamRepository.getByID(4), 3, 1));
        games.put(2L, new Game(2L, "Soccer", "2023-11-05T09:15AM", "Greenfield Stadium", teamRepository.getByID(1), teamRepository.getByID(3), 2, 2));
        games.put(3L, new Game(3L, "Baseball", "2023-11-07T07:45PM", "Diamond Park", teamRepository.getByID(5), teamRepository.getByID(0), 4, 3));
        games.put(4L, new Game(4L, "Hockey", "2023-11-10T04:00PM", "Ice Palace", teamRepository.getByID(2), teamRepository.getByID(4), 1, 1));
        games.put(5L, new Game(5L, "Volleyball", "2023-11-12T01:20PM", "Sunset Beach Court", teamRepository.getByID(1), teamRepository.getByID(3), 3, 2));
    }
    
}
