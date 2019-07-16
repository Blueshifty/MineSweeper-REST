package com.example.openshift_deneme.controller;

import com.example.openshift_deneme.models.Game;
import com.example.openshift_deneme.models.Move;
import com.example.openshift_deneme.service.GameService;
import com.example.openshift_deneme.json.JsonSchemaValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Optional;
//import javax.validation.Valid;
import java.util.List;


@RestController     
@RequestMapping("/game")
@Api(value = "Game Api", description = "Operations to Play the Game and Create a Game")
public class GameController {
    
    @Autowired
    GameService gameService;

    @ApiOperation(value = "Get a Game By ID")
    @GetMapping("/{id}")
    public Optional<Game> getGame(@ApiParam(value = "Game ID from which Game object will retrieve", required = true)
        @PathVariable("id") String gameId){
       return  gameService.getGame(gameId);
    }

    @ApiOperation(value = "View The List of Open Games", response = List.class)
    @GetMapping("/allgame")
    public List<Game> getAllGame(){
        return gameService.getAllGame();
    }

    @ApiOperation(value = "Play a Game By ID")
    @PutMapping("/play/{id}")
    public ResponseEntity<String> play(@ApiParam(value = "Game ID from which Game to Play", required = true)    
        @PathVariable("id") String gameId,
        @ApiParam(value = "Coordinates to Play on Game", required = true)
        @JsonSchemaValidate(schemaPath =  "/MoveSchema.json") Move move){
        String message = gameService.play(gameId, move);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a Game That Has 10x10 Matrix To Play With")
    @PostMapping("/create")
    public String createGame(){
        return gameService.addGame();
    }

    @ApiOperation(value = "Delete Game By ID")
    @DeleteMapping("/delete/{id}")
    public void deleteMatrix(@ApiParam(value = "Game ID to Delete From Database", required = true)
        @PathVariable("id") String gameId){
        gameService.deleteGame(gameId);
    }

    @ApiOperation(value = "Delete All Games")
    @DeleteMapping("/delete/all")
    public void deleteAllGame(){
        gameService.deleteAllGame();    
    }

}

