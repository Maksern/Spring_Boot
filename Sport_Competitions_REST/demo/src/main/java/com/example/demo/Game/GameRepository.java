package com.example.demo.Game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Game.Models.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{

}
