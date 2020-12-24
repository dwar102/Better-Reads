import { Component, OnInit, EventEmitter, Output, OnChanges } from '@angular/core'
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.styl']
})
export class RegisterComponent implements OnInit {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  loggedUser: User;
  userInput: string;
  passInput: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

  }

  register() {
    if (this.userInput && this.passInput) {
      console.log('Registering ' + this.userInput);
      this.userService.registerUser(this.userInput, this.passInput).subscribe();
      //Log in after registered
      /*this.userService.loginUser(this.userInput, this.passInput).subscribe(
        resp => {
          this.loggedUser = resp;
          this.logInEvent.emit();
        }
      );*/
    } else {
      alert('Please fill out both fields');
    }
  }

}
