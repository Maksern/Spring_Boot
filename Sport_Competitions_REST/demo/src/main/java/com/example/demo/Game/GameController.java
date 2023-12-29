package com.example.demo.Game;

import java.net.URI;
import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Game.Models.Game;
import com.example.demo.Game.Models.GameCreateDTO;
import com.example.demo.Game.Models.GameJpaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
@Tag(name = "Game", description = "CRUD operation for work with Game class")
public class GameController {

    private final GameService gameService;

    
    @Operation(summary = "Create Game", description = "Create Game with id generate on server side",
                parameters = {@Parameter(name = "gameDto", description = "New game parameters")})
    @ApiResponses({@ApiResponse(responseCode = "201", description = "The game was created"),
                  @ApiResponse(responseCode = "400", description = "Not Valid GameDTO", content = @Content)})

    @PostMapping
    ResponseEntity<GameJpaDTO> createGame(@RequestBody @Valid GameCreateDTO gameDto) {
        GameJpaDTO game = gameService.createGame(gameDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getGameid()).toUri();
        return ResponseEntity.created(uri).body(game);
    }

    @Operation(summary = "Create more than one game", description = "Create a couple of game with different parameters",
                parameters = {@Parameter(name = "gameDto", description = "The list of parameters for new games")})
    @ApiResponses({@ApiResponse(responseCode = "201", description = "The games was created"),
                  @ApiResponse(responseCode = "400", description = "Not Valid GameDTO", content = @Content)})
    
    @PostMapping("/create")
    ResponseEntity<Iterable<GameJpaDTO>> createGame(@RequestBody @Valid GameCreateDTO[] gameDto) {
        Iterable<GameJpaDTO> games = gameService.createGames(gameDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(games.iterator().next()).toUri();
        return ResponseEntity.created(uri).body(games);
    }

    @Operation(summary = "Get all Games", description = "Returns all games from database")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Get all teams from database"),
                  @ApiResponse(responseCode = "404", description = "Team table is empty", content = @Content)})

    @GetMapping
    public ResponseEntity<Iterable<GameJpaDTO>> getAll(){
        Iterable<GameJpaDTO> games = gameService.getAll();
        Iterator<GameJpaDTO> gameIterator = games.iterator(); 

        if(!gameIterator.hasNext()){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }


    @Operation(summary = "Get page with Teams", description = "Return a page with default number of games",
            parameters = {@Parameter(name = "pageNumber", description = "Number of search page", example = "2")})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Find needed games and return page"),
                  @ApiResponse(responseCode = "404", description = "Database not have element for this page", content = @Content)})

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<Iterable<GameJpaDTO>> getPage(@PathVariable int pageNumber, @RequestParam(defaultValue = "5") int pageSize){
        Iterable<GameJpaDTO> games = gameService.getPage(pageNumber, pageSize);
        Iterator<GameJpaDTO> gameIterator = games.iterator(); 

        if(!gameIterator.hasNext()){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }


    @Operation(summary = "Get page with specific atributes", description = "Return a page with default number of games")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Find needed games and return them"),
                  @ApiResponse(responseCode = "404", description = "Database not have element for this search", content = @Content)})

    @GetMapping("/search-sport/{sportType}")
    public ResponseEntity<Iterable<GameJpaDTO>> searchGamesBySport(@PathVariable String sportType){
        Iterable<GameJpaDTO> games = gameService.getBySport(sportType);
        Iterator<GameJpaDTO> gameIterator = games.iterator(); 

        if(!gameIterator.hasNext()){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/search-date/{date}")
    public ResponseEntity<Iterable<GameJpaDTO>> searchGamesByTime(@PathVariable String date){
        Iterable<GameJpaDTO> games = gameService.getByTime(date);
        Iterator<GameJpaDTO> gameIterator = games.iterator(); 

        if(!gameIterator.hasNext()){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Game by ID", description = "Attempting to find Game by the specified ID. If errors occur, returned Not Found status code",
                parameters = {@Parameter(name = "id", description = "Game Identifier", example = "4")})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The game was find"),
                  @ApiResponse(responseCode = "404", description = "The game with specified Id not found", content = @Content)})
    ResponseEntity<GameJpaDTO> getByID(@PathVariable Long id) {
        try {
            GameJpaDTO game = gameService.getByID(id);
            return ResponseEntity.ok(game);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Update Game by ID", description = "Change the old value with new for Game by the specified ID",
                parameters = {@Parameter(name = "gameDto", description = "Game DTO")})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The game was find and updated"),
                  @ApiResponse(responseCode = "400", description = "The game have bad id", content = @Content)})

    @PutMapping("/{id}")
    ResponseEntity<GameJpaDTO> updateGame(@RequestBody GameCreateDTO gameDto, @PathVariable Long id) {
        try {
            GameJpaDTO game = gameService.updateGame(id, gameDto);
            return ResponseEntity.ok(game);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Game by ID", description = "Attempting to find Game by the specified ID and delete it",
                parameters = {@Parameter(name = "id", description = "Game Identifier", example = "4")})
    @ApiResponses(@ApiResponse(responseCode = "204", description = "The game was find and deleted"))

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id) {
        gameService.deleteGameById(id);
    }
}
