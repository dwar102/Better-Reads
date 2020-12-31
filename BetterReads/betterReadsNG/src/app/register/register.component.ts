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
  @Output() registerEvent: EventEmitter<any> = new EventEmitter();
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  loggedUser: User;
  userInput: string;
  passInput: string;
  confirm: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

  }

  register() {
    if (this.userInput && this.passInput && this.confirm) {
      console.log(this.userInput + " " + this.passInput + " " + this.confirm);
      if (this.passInput == this.confirm) {
        console.log('Registering ' + this.userInput);
        this.userService.registerUser(this.userInput, this.passInput).subscribe(
          resp => {
                this.registerEvent.emit();
          }
        );
      } else {
        alert('Password and confirmation do not match');
      }
    } else {
      alert('Please fill all fields');
    }
  }

}
