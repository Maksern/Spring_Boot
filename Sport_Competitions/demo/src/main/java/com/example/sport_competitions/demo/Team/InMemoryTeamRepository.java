package com.example.sport_competitions.demo.Team;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTeamRepository implements TeamRepository{
    Map<Long, Team> Teams = new TreeMap<>(); 

    @Override
    public Team getByID(long id) {
        return Teams.get(id);
    }

    @Override
    public Iterable<Team> getAll() {
        return Teams.values();
    }

    @Override
    public Team save(Team Team) {
        Teams.put(Team.getId(), Team);
        return Team;
    }

    @Override
    public void delete(long id) {
        Teams.remove(id);
    }

    public InMemoryTeamRepository(){
        Teams.put(0L, new Team(0L, "Football", "Spartak", 14));
        Teams.put(1L, new Team(1L, "Football", "Barcelona", 22));
        Teams.put(2L, new Team(2L, "Basketball", "Lakers", 10));
        Teams.put(3L, new Team(3L, "Cricket", "Mumbai Indians", 11));
        Teams.put(4L, new Team(4L, "Baseball", "Yankees", 27));
    }
}
