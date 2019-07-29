import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { stringify } from 'querystring';
import {Game} from '../game';

@Injectable({
  providedIn: 'root'
})


export class HttpClientService {
  api : string = "http://localhost:8080/game/";

  constructor(
    private httpClient:HttpClient
  ) {}
    
  public getGame(gameId:string){
    return this.httpClient.get<Game>(this.api+ gameId);
  }

  public getGames(){
    return this.httpClient.get<Game[]>(this.api + "allgame");
  }

  public getVisibBoard(gameId: string){
    return this.httpClient.get<any[][]>(this.api+"visible/"+gameId);
  }

  public createGame(){
    return this.httpClient.get<Game>(this.api+ "create");
  }

  public deleteGame(game:Game) {
    return this.httpClient.delete<Game>( this.api +"delete/"+ game.id);
  }

  public deleteAllGames(){
   return  this.httpClient.delete(this.api + "delete/all");
  }

  public playGame(gameId:string, x: number, y: number){
    let responseT: Object = {
      responseType :'text',
    }
    return this.httpClient.put<string>(this.api + "play/"+gameId,{"x": x, "y": y}, responseT);
  }
}

