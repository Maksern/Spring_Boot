package com.example.sport_competitions.demo.Game;

public interface GameRepository {
    Game getByID(long id);
    Iterable<Game> getAll();

    Game save(Game game);

    void delete(long id);
}
