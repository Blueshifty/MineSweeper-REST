import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MineSweeperComponent } from './mine-sweeper/mine-sweeper.component';
import { HttpClientModule } from '@angular/common/http';
import { PlayComponent } from './play/play.component';
import { MineComponent } from './mine/mine.component';
@NgModule({
  declarations: [
    AppComponent,
    MineSweeperComponent,
    PlayComponent,
    MineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
