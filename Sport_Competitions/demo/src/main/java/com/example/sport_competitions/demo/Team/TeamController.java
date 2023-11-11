package com.example.sport_competitions.demo.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @PostMapping("/create")
    public Team create(@RequestBody Team team){
        return teamRepository.save(team);
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Team> findAll(){
        return teamRepository.getAll();
    }

    @GetMapping("/")
    @ResponseBody
    public Team findByID(@RequestParam long id){
        return teamRepository.getByID(id);
    }

    @PutMapping("/")
    @ResponseBody
    public Team update(@RequestBody Team team, @RequestParam long id){
        return teamRepository.save(team);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Team delete(@RequestParam long id){
        Team team = teamRepository.getByID(id);
        teamRepository.delete(id);
        return team;
    }
    
}
