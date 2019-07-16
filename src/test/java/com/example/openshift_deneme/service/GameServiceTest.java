package com.example.openshift_deneme.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.openshift_deneme.OpenshiftDenemeApplication;
import com.example.openshift_deneme.service.GameService;
import com.example.openshift_deneme.models.Game;
import com.example.openshift_deneme.models.Move;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.Optional;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {OpenshiftDenemeApplication.class})
@WebAppConfiguration

public class GameServiceTest{

    @Autowired
    private GameService service;


    @Test
    public void createGameTest()throws Exception{
        String id = service.addGame();
        assertThat(id).isNotEmpty();
    }

    @Test
    public void getInvaildGameTest()throws Exception{
        Optional<Game> invalidGame = service.getGame("Invaild Game ID");
        assertThat(!invalidGame.isPresent());
    }

    @Test
    public void getValidGameTest()throws Exception{
        String id = service.addGame();
        Optional<Game> validGame = service.getGame(id);
        assertThat(validGame.isPresent());
    }

    @Test
    public void deleteGameTest() throws Exception{
        String id = service.addGame();
        service.deleteGame(id);
        Optional<Game> game = service.getGame(id);
        assertThat(!game.isPresent());
    }

    @Test
    public void deleteAllGameTest() throws Exception{
        String[] idValues = {
            service.addGame(), service.addGame(), service.addGame() };
        service.deleteAllGame();
        for(int i = 0; i<3; ++i){
            assertThat(!service.getGame(idValues[i]).isPresent());
        }
    }

    @Test
    public void getAllGameTest(){
        service.deleteAllGame();
        String[] idValues = {
            service.addGame(), service.addGame(), service.addGame()};
        List<Game> games = service.getAllGame();
        assertThat(games.size()).isEqualTo(3);
        for(int i = 0; i<3;++i){
            assertThat(games.get(i).getId()).isEqualTo(idValues[i]);
        }
    }

    @Test
    public void mineTest()throws Exception{
        int mineCount = 0;
        String id = service.addGame();
        Game game = service.getGame(id).get();
        char[][] mineBoard = game.getBoard();
        for(int i = 0; i< 10; ++i){
            for(int j = 0; j < 10; ++j){
                if(mineBoard[i][j]=='X'){
                    mineCount++;
                }
            }
        }
        assertThat(mineCount).isEqualTo(25);
    }

    @Test
    public void invisBoardTest()throws Exception{
        String id = service.addGame();
        Game game = service.getGame(id).get();
        char[][] invisBoard = game.getInvisBoard();
        for(int i = 0; i < 10; ++i){
            for(int j = 0; j < 10; ++j){
                assertThat(invisBoard[i][j]).isEqualTo('#');
            }
        }    
    }

    @Test
    public void playNotToMineTest()throws Exception{
        Move move = new Move(0,0);
        String id  = service.addGame();
        Game game = service.getGame(id).get();
        char[][] board = game.getBoard();
        for(int i = 0; i< 10; ++i){
            for(int j = 0; j<10; ++j){
                if(board[i][j] == '#'){
                    move.setX(i);
                    move.setY(j);
                    break;
                }
            }
        }

        String message = service.play(id, move);
        assertThat(message).isEqualTo("Mayin Yok, Devam Edin !");
        assertThat(service.getGame(id).get().getBoard()
        [move.getX()][move.getY()]).isEqualTo('$');    
    }
    @Test
    public void playToMineTest() throws Exception{
        String id = service.addGame(), message = "ZoSo";
        Move move = new Move();
        Game game = service.getGame(id).get();
        char[][] board = game.getBoard();
        for(int i = 0; i< 10; ++i){
            for(int j = 0; j <10; ++j){
                if(board[i][j] == 'X'){
                    move.setX(i);
                    move.setY(j);
                    break;
                }
            }
        }
        message = service.play(id, move);
        assertThat(message).isEqualTo("Oyun Bitti, Kaybettiniz :(");
        assertThat(!service.getGame(id).isPresent());
    }

    @Test
    public void playTheSamePlaceTest() throws Exception{
        Move move = new Move();
        String id = service.addGame();
        Game game = service.getGame(id).get();
        char[][] board = game.getBoard();
        for(int i = 0; i< 10; ++i){
            for(int j = 0; j< 10; ++j){
                if(board[i][j] == '#'){
                    move.setX(i);
                    move.setY(j);
                    break;
                }
            }
        }
        service.play(id, move);
        String message = service.play(id, move);
        assertThat(message).isEqualTo("Onceden Secilmis Yeri Sectiniz !");
    }

    @Test
    public void gameEndTest() throws Exception{
        int moveCount = 0;
        String id = service.addGame(), message = "ZoSo";
        Game game = service.getGame(id).get();
        char[][] board = game.getBoard();
        for(int i = 0; i< 10; ++i){
            for(int j = 0; j< 10; ++j){
                if(board[i][j]=='#'){
                    if(moveCount == 74){
                        message = service.play(id, new Move(i,j));
                    }
                    service.play(id, new Move(i,j));
                    moveCount++;
                }
            }
        }    
        assertThat(message).isEqualTo("Tebrikler Kazandiniz :)");
        assertThat(!service.getGame(id).isPresent());
    }

}