package com.example.sport_competitions.demo.Game;

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
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("/create")
    public Game create(@RequestBody GameCreateDTO dto){
        return gameService.create(dto);
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Game> findAll(){
        return gameService.getAll();
    }

    @GetMapping("/")
    @ResponseBody
    public Game findByID(@RequestParam long id){
        return gameService.getById(id);
    }

    @PutMapping("/")
    @ResponseBody
    public Game update(@RequestBody GameCreateDTO dto, @RequestParam long id){
        return gameService.update(dto, id);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Game delete(@RequestParam long id){
        return gameService.delete(id);
    }
    
}
