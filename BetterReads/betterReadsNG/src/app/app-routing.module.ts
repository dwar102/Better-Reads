import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShelfComponent } from './shelf/shelf.component';
import { AddmediaComponent } from './addmedia/addmedia.component';

const routes: Routes = [
  {path:'shelf', component:ShelfComponent},
  {path:'addmedia', component:AddmediaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
