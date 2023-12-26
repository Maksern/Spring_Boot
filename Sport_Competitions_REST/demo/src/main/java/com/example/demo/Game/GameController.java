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
import com.example.demo.Game.Models.GameDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
@Tag(name = "Game", description = "CRUD operation for work with Game class")
public class GameController {

    private final GameService gameService;

    @PostMapping
    @Operation(summary = "Create Game", description = "Create Game with id generate on server side",
                parameters = {@Parameter(name = "gameDto", description = "New game parameters")})
    @ApiResponses({@ApiResponse(responseCode = "201", description = "The game was created"),
                  @ApiResponse(responseCode = "400", description = "Not Valid GameDTO", content = @Content)})
    ResponseEntity<Game> createGame(@RequestBody GameDTO gameDto) {
        Game game = gameService.createGame(gameDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(game.getId()).toUri();
        return ResponseEntity.created(uri).body(game);
    }

    @GetMapping
    @Operation(summary = "Get all Games", description = "Returns all games from database")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Get all teams from database"),
                  @ApiResponse(responseCode = "404", description = "Team table is empty", content = @Content)})
    public ResponseEntity<Iterable<Game>> getAll(){
        Iterable<Game> games = gameService.getAll();
        Iterator<Game> gameIterator = games.iterator(); 

        if(!gameIterator.hasNext()){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/page/{pageNumber}")
    @Operation(summary = "Get page with Teams", description = "Return a page with default number of games",
            parameters = {@Parameter(name = "pageNumber", description = "Number of search page", example = "2")})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Find needed games and return page"),
                  @ApiResponse(responseCode = "404", description = "Database not have element for this page", content = @Content)})
    public ResponseEntity<Iterable<Game>> getPage(@PathVariable int pageNumber, @RequestParam(defaultValue = "5") int pageSize){
        Iterable<Game> games = gameService.getPage(pageNumber, pageSize);
        Iterator<Game> gameIterator = games.iterator(); 

        if(!gameIterator.hasNext()){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Get page with specific atributes", description = "Return a page with default number of games")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Find needed games and return them"),
                  @ApiResponse(responseCode = "404", description = "Database not have element for this search", content = @Content)})
    public ResponseEntity<Iterable<Game>> searchGames(@RequestParam(defaultValue = "all") String sportType){
        Iterable<Game> games = gameService.getBySport(sportType);
        Iterator<Game> gameIterator = games.iterator(); 

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
    ResponseEntity<Game> getByID(@PathVariable Long id) {
        try {
            Game game = gameService.getByID(id);
            return ResponseEntity.ok(game);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Game by ID", description = "Change the old value with new for Game by the specified ID",
                parameters = {@Parameter(name = "gameDto", description = "Game DTO")})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The game was find and updated"),
                  @ApiResponse(responseCode = "400", description = "The game have bad id", content = @Content)})
    ResponseEntity<Game> updateGame(@RequestBody GameDTO gameDto, @PathVariable Long id) {
        try {
            Game game = gameService.updateGame(id, gameDto);
            return ResponseEntity.ok(game);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Game by ID", description = "Attempting to find Game by the specified ID and delete it",
                parameters = {@Parameter(name = "id", description = "Game Identifier", example = "4")})
    @ApiResponses(@ApiResponse(responseCode = "204", description = "The game was find and deleted"))
    public void deleteByID(@PathVariable Long id) {
        gameService.deleteGameById(id);
    }
}
