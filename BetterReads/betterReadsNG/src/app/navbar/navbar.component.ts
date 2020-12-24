import { Component, OnChanges, OnInit, EventEmitter, Output } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.styl']
})
export class NavbarComponent implements OnInit, OnChanges {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  loggedUser: User;
  user: string;
  pass: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.user = '';
    this.pass = '';
    this.logIn();
  }

  ngOnChanges() {
    console.log(this.user + ' ' + this.pass);
  }

  logIn() {
    console.log(this.user + ' ' + this.pass);
    this.userService.loginUser(this.user, this.pass).subscribe(
      resp => {
        this.loggedUser = resp;
        this.logInEvent.emit();
      }
    );
  }

  logOut() {
    this.userService.logoutUser().subscribe(
      resp => {
        this.loggedUser = null;
        this.router.navigate(['home']);
      }
    );
  }

  signUp() {
    this.router.navigateByUrl('register');
  }

}
