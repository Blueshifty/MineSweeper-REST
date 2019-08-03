package com.example.openshiftDeneme.service;

import com.example.openshiftDeneme.models.Game;
import com.example.openshiftDeneme.models.Move;
import com.example.openshiftDeneme.repositories.GameRepository;

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

    public Game getGame(String id){
        Optional<Game> game = gameRepository.findById(id);
        if(!game.isPresent()){
            return null;}
        return game.get();
    }

    public List<Game> getAllGame(){
        return gameRepository.findAll();
    }
    
    public char[][] getVisibBoard(String id){
        Optional<Game> game = gameRepository.findById(id);
        if(!game.isPresent()){
            return null; }
        return game.get().getVisibBoard();
    }

    public Game addGame(){
        Game game = new Game();
        int[][] board = game.getBoard();
        char[][] invisBoard = game.getVisibBoard();
        for(int i = 0; i<10; ++i){
            Arrays.fill(board[i], 0);
            Arrays.fill(invisBoard[i], '#');
        }
        mineBoard(board);
        gameRepository.save(game);
        return game;
    }

    public String play(String gameId, Move move){
        Optional<Game> game = gameRepository.findById(gameId);
        if(!game.isPresent()){
            return "Invalid Game!";
        }
        String message = "ZoSo";
        int x = move.getX(), y = move.getY();
        int[][] board = game.get().getBoard();  
        char[][] invisBoard = game.get().getVisibBoard();
        if(board[x][y] == 9){
            gameRepository.deleteById(gameId);
            message = "Game Over! (Game Has Been Deleted From DB)";
        } else if(invisBoard[x][y] != '#'){
            message = "That Place is Opened Already !";
        } else{
            playtoBoard(x, y, board, invisBoard);
            if(isGameOver(invisBoard)){
                gameRepository.deleteById(gameId);
                message = "Gratz You Win !! (Game Has Been Deleted From DB)";
            } else{
            gameRepository.save(game.get());
            message = "Well played, Go on!";
            }
        }
        return message;
    }

    public void deleteGame(String gameId){
        gameRepository.deleteById(gameId);
    }
    
    public void deleteAllGame(){
        gameRepository.deleteAll();
    }

    private void playtoBoard(int x, int y, int[][] board, char[][] visibBoard){
        if(board[x][y] == 0){
            visibBoard[x][y] = '0';
            for(int i = x-1; i <= x+1; ++i ){
                for(int j = y-1; j <= y+1;  ++j){
                    if(i < 0 || i > 9 || j < 0 || j > 9 || (x == i && y == j) || visibBoard[i][j] != '#'){
                        continue;
                    }else if(board[i][j] == 0){
                        playtoBoard(i, j, board, visibBoard);
                    }else{
                    visibBoard[i][j] = Integer.toString(board[i][j]).charAt(0);
                    }
                }
            }
        } else{ 
            visibBoard[x][y] = Integer.toString(board[x][y]).charAt(0);
        }
    }

    private void  mineBoard(int[][] board){
        Random rand = new Random();
        int x = 0, y = 0, mine = 0;
        while(mine <= 10){
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            if(board[x][y] != 9){
                board[x][y] = 9;
                valueSpace(x, y, board);
                mine+=1;
            }
        }
    }

    private void valueSpace(int x, int y, int[][] board){
        for(int i = x-1; i <= x+1; ++i ){
            for(int j = y-1; j <= y+1;  ++j){
                if(i < 0 || i > 9 || j < 0 || j > 9 || board[i][j] == 9 || (x==i && y==j)){
                    continue;
                }
                board[i][j]+=1;
            }
        }
    }

    private boolean isGameOver(char[][] board){
        int count = 0;
        for(int i = 0; i< 10; ++i){
            for(int j = 0; j < 10; ++j){
                if(board[i][j] == '#'){
                    if(count++ > 10){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
