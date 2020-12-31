import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent {
  title = 'betterReadsNG';
  showSearch: boolean = true;
  showRegister: boolean = false;

  setLogin() {
    console.log('user logged in');
  }

  logIn() {
    this.showSearch = false;
    this.showRegister = false;
  }

  logOut() {
    this.showSearch = true;
  }

  registrationComplete() {
    this.showRegister = false;
    this.showSearch = true;
  }

  registrationStart() {
    this.showRegister = true;
    this.showSearch = false;
  }

}
