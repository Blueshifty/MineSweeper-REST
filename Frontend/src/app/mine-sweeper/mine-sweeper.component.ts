import { Component, OnInit } from '@angular/core';
import { HttpClientService} from '../service/http-client.service';
import {Game} from '../game';

@Component({
  selector: 'app-mine-sweeper',
  templateUrl: './mine-sweeper.component.html',
  styleUrls: ['./mine-sweeper.component.css']
})
export class MineSweeperComponent implements OnInit {

  games:Game[];
  
  constructor(
    private httpClientService:HttpClientService
  ) {}

    ngOnInit(){
      this.getAllGame();
    }

    getAllGame(){
      this.httpClientService.getGames().subscribe(
        response => {this.games = response;})
    }

    createGame(){
      this.httpClientService.createGame().subscribe(
        response => {this.games.push(response);})
    }

    deleteGame(game:Game){
      this.httpClientService.deleteGame(game).subscribe(
        data => this.getAllGame());
    }

   deleteAllGame(){
      this.httpClientService.deleteAllGames().subscribe(
        data => this.getAllGame());
   }

}
