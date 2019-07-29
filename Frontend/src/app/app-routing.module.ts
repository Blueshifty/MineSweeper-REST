import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MineSweeperComponent } from './mine-sweeper/mine-sweeper.component';
import { PlayComponent } from './play/play.component';

const routes: Routes = [
  {path: '' , component: MineSweeperComponent},
  {path: 'play/:id', component: PlayComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}