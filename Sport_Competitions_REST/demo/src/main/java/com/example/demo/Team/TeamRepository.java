package com.example.demo.Team;


public interface TeamRepository {
    Team getByID(Long id);
    Iterable<Team> getAll();

    Team save(Team team);

    void delete(Long id);
}
