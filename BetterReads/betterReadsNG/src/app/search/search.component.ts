import { Component, OnChanges, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl']
})
export class SearchComponent implements OnInit {
  public loggedUser: User; 
  public searchContent: string;
  public searchActivated: boolean = false;
  public searchType = 'title';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    //this.checkLogin();
  }

  setType(type: string) {
    this.searchType = type;
  }

  search() {
    if (this.searchType == 'title') {
      console.log(' title: ' + this.searchContent);
    } else {
      console.log(' author: ' + this.searchContent);
    }
    this.searchActivated = true;
  }

  checkLogin() {
    this.userService.loginUser(undefined, undefined).subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );
    console.log(this.loggedUser);
  }
  
}
