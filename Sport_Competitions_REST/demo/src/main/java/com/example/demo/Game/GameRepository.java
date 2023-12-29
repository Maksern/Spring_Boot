package com.example.demo.Game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Game.Models.Game;
import java.sql.Timestamp;


@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
    // @Query("SELECT g FROM Game g WHERE g.sportType=?1")
    // List<Game> findBySportType(String sportType);

    @Query(value = "SELECT * FROM games WHERE sportType=?1", nativeQuery = true)
    List<Game> findBySportType(String sportType);

    List<Game> findByGameTimeAfter(Timestamp gameTime);
}
