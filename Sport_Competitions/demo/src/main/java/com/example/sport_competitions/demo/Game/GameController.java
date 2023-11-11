package com.example.sport_competitions.demo.Game;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sport_competitions.demo.Team.Team;
import com.example.sport_competitions.demo.Team.TeamRepository;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;

    @Autowired
    TeamRepository teamRepository;

    @PostMapping("/create")
    public String create(GameCreateDTO dto){
        gameService.create(dto);
        return "redirect:/game/page/1";
    }

    @GetMapping("/form")
    public String createForm(Model model){
        Iterable<Team> teams = teamRepository.getAll();
        model.addAttribute("teams", teams);
        return "game/CreateForm";
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Game> findAll(){
        return gameService.getAll();
    }

    @GetMapping("/page/{pageNumber}")
    public String findPage(@PathVariable int pageNumber, Model model){
        int pageSize = 6;
        Iterable<Game>  games = gameService.getPage(pageNumber-1, pageSize);
        model.addAttribute("games", games);
        model.addAttribute("gamesSize", IterableUtils.size(gameService.getAll()));
        model.addAttribute("pageSize", pageSize);
        return "main";
    }


    @GetMapping("/{id}")
    public String findByID(@PathVariable long id, Model model){
        Game game = gameService.getById(id);
        model.addAttribute("game", game);
        return "game/GamePage";
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public Game update(@RequestBody GameCreateDTO dto, @PathVariable long id){
        return gameService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        gameService.delete(id);
        return "redirect:/game/page/1";
    }
}
