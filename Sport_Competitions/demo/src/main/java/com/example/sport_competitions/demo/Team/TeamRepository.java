package com.example.sport_competitions.demo.Team;

public interface TeamRepository {
    Team getByID(long id);
    Iterable<Team> getAll();

    Team save(Team Team);

    void delete(long id);
}
