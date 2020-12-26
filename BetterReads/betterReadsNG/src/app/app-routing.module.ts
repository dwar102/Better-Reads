import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { ShelfComponent } from './shelf/shelf.component';
import { ProfileComponent } from './profile/profile.component';
import { AddmediaComponent } from './addmedia/addmedia.component';

const routes: Routes = [
  {path:'addmedia', component:AddmediaComponent},
  {path:'shelf', component:ShelfComponent},
  {path:'register', component:RegisterComponent},
  {path:'profile', component:ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
