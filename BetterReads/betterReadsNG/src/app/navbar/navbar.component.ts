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
  @Output() logOutEvent: EventEmitter<any> = new EventEmitter();
  @Output() registerEvent: EventEmitter<any> = new EventEmitter();
  loggedUser: User;
  user: string;
  pass: string;
  showProfile: boolean = true;
  addMedia: boolean = false;

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
  test() {
    this.router.navigateByUrl('writeComment');
  }
  logOut() {
    this.userService.logoutUser().subscribe(
      resp => {
        this.loggedUser = null;
        this.logOutEvent.emit();
      }
    );
  }

  signUp() {
    this.registerEvent.emit();
  }

  viewProfile() {
    this.showProfile = true;
  }

  hideProfile() {
    this.showProfile = false;
  }

  register() {
    this.registerEvent.emit();
  }

  addSomeMedia() {
    this.addMedia = true;
  }

  mediaAdded() {
    this.addMedia = false;
  }

  home() {

  }

}
