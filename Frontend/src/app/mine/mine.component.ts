import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { HttpClientService} from '../service/http-client.service';
import {Mine} from '../mine';
import {Game} from '../game';

@Component({
  selector: 'app-mine',
  templateUrl: './mine.component.html',
  styleUrls: ['./mine.component.css']
})
export class MineComponent implements OnInit {

  @Input() mine: Mine;
  @Output() LeftClick = new EventEmitter();

  constructor(
    private httpClientService:HttpClientService
  ) { }

  ngOnInit() { }

  leftClick(){
        this.LeftClick.emit({x: this.mine.x, y: this.mine.y});
  }
}