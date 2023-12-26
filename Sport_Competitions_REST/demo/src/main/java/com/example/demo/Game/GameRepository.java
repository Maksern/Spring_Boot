package com.example.demo.Game;

import org.springframework.stereotype.Repository;

import com.example.demo.Game.Models.Game;

@Repository
public interface GameRepository{
    Game getByID(long id);
    Iterable<Game> getAll();

    Game save(Game game);

    void delete(long id);
}
