import { Component, OnInit } from '@angular/core';
import { HttpClientService} from '../service/http-client.service';
import { ActivatedRoute } from '@angular/router';
import { Mine } from "../mine";
import { Game } from "../game";

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css'],
})
export class PlayComponent implements OnInit {
  
  message : string = "Make a Move !";
  board : Mine[][] = [];
  game : Game;

  constructor(
    private httpClientService:HttpClientService,
    private route:ActivatedRoute, 
  ) {}

  
  ngOnInit() {
    this.getGame();
  }

  leftClick(event) {
      this.playGame(event.x, event.y);
  }

  getGame(){
    this.httpClientService.getGame(this.route.snapshot.params.id).subscribe(
      response => {this.initalizeBoard(response);
                   this.game = response;})
  }
  
  changeBoard(){
    this.httpClientService.getVisibBoard(this.route.snapshot.params.id).subscribe(
      response => { 
        for(let i = 0; i< 10; i++){
          for(let j = 0 ; j< 10; j++){
            this.board[i][j].value = response[i][j];
          }
        }
      })
  }

  initalizeBoard(response: Game){
    for(let i = 0; i < 10; i++){
      let list : Mine[] = [];
      for(let j = 0; j < 10; j++){
        let object : Mine = { x: i, y: j, value: response.invisBoard[i][j]};
        list.push(object);
      }
     this.board.push(list);
    }
  }
  
  showBombs(){
    for(let i = 0; i< 10; ++i){
      for(let j = 0; j< 10; ++j){
        if(this.game.board[i][j] == 9){
          this.board[i][j].value = 9;
        }
      }
    }
  }

  showAllBoard(){
    for(let i = 0; i<10; ++i){
      for(let j = 0; j<10; ++j){
        this.board[i][j].value = this.game.board[i][j];
      }
    }
  }

  playGame(x: number, y: number){
    this.httpClientService.playGame(this.route.snapshot.params.id, x,y).subscribe(
      response => {this.message = response; 
        if(response == "Game Over! (Game Has Been Deleted From DB)"){
          this.showBombs();
        }if(response == "Gratz You Win !! (Game Has Been Deleted From DB)"){
          this.showAllBoard();
        }if(response == "That Place is Opened Already !"){
          return;
        }else{ 
        this.changeBoard();}
      })
  }
}