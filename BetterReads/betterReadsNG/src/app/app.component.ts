import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent {
  title = 'betterReadsNG';
  notLoggedIn: boolean = true;

  setLogin() {
    console.log('user logged in');
  }

  logIn() {
    this.notLoggedIn = false;
  }

  logOut() {
    this.notLoggedIn = true;
  }

}
