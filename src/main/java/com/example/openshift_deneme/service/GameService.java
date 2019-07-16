package com.example.openshift_deneme.service;

import com.example.openshift_deneme.models.Game;
import com.example.openshift_deneme.models.Move;
import com.example.openshift_deneme.repositories.GameRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Arrays;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Optional<Game> getGame(String id){
        return gameRepository.findById(id);
    }

    public List<Game> getAllGame(){
        return gameRepository.findAll();
    }

    public String addGame(){
        Game game = new Game();
        char[][] board = game.getBoard();
        char[][] invisBoard = game.getInvisBoard();
        for(int i = 0; i<10; ++i){
            Arrays.fill(board[i], '#');
            Arrays.fill(invisBoard[i], '#');
        }
        mineBoard(board);
        gameRepository.save(game);
        return game.getId();
    }

    public String play(String gameId, Move move){
        Optional<Game> game = gameRepository.findById(gameId);
        if(!game.isPresent()){
            return "Bu Id'ye Ait Oyun Bulunamadi.";
        }
        String message = "ZoSo";
        int x = move.getX(), y = move.getY();
        char[][] board = game.get().getBoard();  
        char[][] invisBoard = game.get().getInvisBoard();
        if(board[x][y] == 'X'){
            gameRepository.deleteById(gameId);
            message = "Oyun Bitti, Kaybettiniz :(";
        }else if(board[x][y] == '$'){
            message = "Onceden Secilmis Yeri Sectiniz !";
        }else{
            board[x][y] = '$';
            invisBoard[x][y] = '$';
            if(isGameOver(board)){
                gameRepository.deleteById(gameId);
                message = "Tebrikler Kazandiniz :)";
            }else{
            gameRepository.save(game.get());
            message = "Mayin Yok, Devam Edin !";            
            }
        }
        return message;
    }

    private void  mineBoard(char[][] board){
        Random rand = new Random();
        int x = 0, y = 0, mine = 0;
        while(mine < 25){
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            if(board[x][y] != 'X'){
                board[x][y] = 'X';
                mine++;
            }
        }
    }

    private boolean isGameOver(char[][] board){
        for(int i = 0; i < 10; ++i){
            for(int j = 0; j < 10; ++j){
                if(board[i][j]=='#'){
                    return false;
                }
            }
        }
        return true;
    }

    public void deleteGame(String gameId){
        gameRepository.deleteById(gameId);
    }
    
    public void deleteAllGame(){
        gameRepository.deleteAll();
    }
}
