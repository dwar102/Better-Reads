import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.styl']
})
export class ProfileComponent implements OnInit {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  @Input() public loggedUser: User; 

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    console.log("logged user: " + this.loggedUser.username);
  }
}
