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

        games.put(0L, new Game(0L, "Football", "2023-11-02 11:00AM", "Magic Street", teamRepository.getByID(0), teamRepository.getByID(2), 5, 2));
        games.put(1L, new Game(1L, "Basketball", "2023-11-03 02:30PM", "Sports Arena", teamRepository.getByID(3), teamRepository.getByID(4), 3, 1));
        games.put(2L, new Game(2L, "Football", "2023-11-05 09:15AM", "Greenfield Stadium", teamRepository.getByID(1), teamRepository.getByID(3), 2, 2));
        games.put(3L, new Game(3L, "Basketball", "2023-11-07 07:45PM", "Diamond Park", teamRepository.getByID(2), teamRepository.getByID(0), 4, 3));
        games.put(4L, new Game(4L, "Swimming", "2023-11-10 04:00PM", "Ice Palace", teamRepository.getByID(2), teamRepository.getByID(4), 1, 1));
        games.put(5L, new Game(5L, "Swimming", "2023-11-12 01:20PM", "Sunset Beach Court", teamRepository.getByID(1), teamRepository.getByID(3), 3, 2));
        games.put(5L, new Game(5L, "Football", "2023-11-12 01:20PM", "Sunset Beach Court", teamRepository.getByID(0), teamRepository.getByID(2), 3, 2));
        games.put(6L, new Game(6L, "Basketball", "2023-11-12 01:30PM", "Downtown Arena", teamRepository.getByID(1), teamRepository.getByID(3), 2, 1));
        games.put(7L, new Game(7L, "Volleyball", "2023-11-12 02:00PM", "Beachside Stadium", teamRepository.getByID(2), teamRepository.getByID(4), 3, 2));
        games.put(8L, new Game(8L, "Swimming", "2023-11-12 02:30PM", "City Pool", teamRepository.getByID(3), teamRepository.getByID(0), 2, 1));
        games.put(9L, new Game(9L, "Hockey", "2023-11-12 03:00PM", "Ice Arena", teamRepository.getByID(4), teamRepository.getByID(1), 4, 3));
        games.put(10L, new Game(10L, "Football", "2023-11-12 03:30PM", "Soccer Park", teamRepository.getByID(0), teamRepository.getByID(2), 3, 2));
        games.put(11L, new Game(11L, "Basketball", "2023-11-12 04:00PM", "High School Gym", teamRepository.getByID(1), teamRepository.getByID(3), 2, 1));
        games.put(12L, new Game(12L, "Volleyball", "2023-11-12 04:30PM", "Community Center", teamRepository.getByID(2), teamRepository.getByID(4), 3, 2));
        games.put(13L, new Game(13L, "Swimming", "2023-11-12 05:00PM", "Aquatic Center", teamRepository.getByID(3), teamRepository.getByID(0), 2, 1));
        games.put(14L, new Game(14L, "Hockey", "2023-11-12 05:30PM", "Skating Rink", teamRepository.getByID(4), teamRepository.getByID(1), 4, 3));

    }
    
}
