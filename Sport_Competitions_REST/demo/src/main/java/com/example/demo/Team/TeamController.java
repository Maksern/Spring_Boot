package com.example.demo.Team;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/teams")
@AllArgsConstructor
@Tag(name = "Team", description = "CRUD operation for work with Team class")
public class TeamController {

    private TeamRepository teamRepository;

    @PostMapping
    @Operation(summary = "Create Team", description = "Create Team with id generate on server side",
                parameters = {@Parameter(name = "teamDto", description = "New team parameters")})
    @ApiResponses({@ApiResponse(responseCode = "201", description = "The team was created"),
                  @ApiResponse(responseCode = "400", description = "The teamDto must be without id", content = @Content)})
    public ResponseEntity<Team> createTeam(@RequestBody Team teamDto){
        if(teamDto.getId() != null){
            ResponseEntity.badRequest().build();
        }
        Team team = teamRepository.save(teamDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(team.getId()).toUri();
        return ResponseEntity.created(uri).body(team);
    }

    @GetMapping
    @Operation(summary = "Get all Teams", description = "Returns all teams from database")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Get all teams from database"),
                  @ApiResponse(responseCode = "404", description = "Team table is empty", content = @Content)})
    public ResponseEntity<Iterable<Team>> getAll(){
        Iterable<Team> teams = teamRepository.getAll();
        Iterator<Team> teamIterator = teams.iterator(); 

        if(!teamIterator.hasNext()){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(teams, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Team by ID", description = "Attempting to find Team by the specified ID. If errors occur, returned Not Found status code",
                parameters = {@Parameter(name = "id", description = "Team Identifier", example = "4")})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The team was find"),
                  @ApiResponse(responseCode = "404", description = "The team with specified Id not found", content = @Content)})
    public ResponseEntity<Team> getByID(@PathVariable long id){
        try {
            Team team = teamRepository.getByID(id);
            return ResponseEntity.ok(team);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Team by ID", description = "Change the old value with new for Team by the specified ID",
                parameters = {@Parameter(name = "teamDto", description = "Team DTO")})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The team was find and updated"),
                  @ApiResponse(responseCode = "400", description = "The team have bad id", content = @Content)})
    public ResponseEntity<Team> updateByID(@PathVariable Long id, @RequestBody Team teamDto){
        if(teamDto.getId() != null && !teamDto.getId().equals(id)){
            return ResponseEntity.badRequest().build();
        }
        Team team = new Team(id, teamDto.getTeamName(), teamDto.getSportType(), teamDto.getPlayerNumber());
        team = teamRepository.save(team);

        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Team by ID", description = "Attempting to find Team by the specified ID and delete it",
                parameters = {@Parameter(name = "id", description = "Team Identifier", example = "4")})
    @ApiResponses(@ApiResponse(responseCode = "204", description = "The team was find and deleted"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID(@PathVariable Long id){
        teamRepository.delete(id);
    }
}
