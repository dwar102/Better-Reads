import { Component, OnChanges, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { MediaService } from '../services/media.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl']
})
export class SearchComponent implements OnInit {
  @Output() searchEvent: EventEmitter<any> = new EventEmitter();
  public loggedUser: User; 
  public searchContent: string;
  public searchActivated: boolean = false;
  public searchResults: any;
  public searchType = 'title';

  constructor(private userService: UserService, private mediaService: MediaService, private router: Router) { }

  ngOnInit(): void {
    //this.checkLogin();
  }

  setType(type: string) {
    this.searchType = type;
  }

  search() {
    this.mediaService.getSearchResults(this.searchType, this.searchContent).subscribe(
      resp => {
        this.searchResults = resp;
        console.log(this.searchResults);
        this.searchActivated = true;
      }
    );
  }

  checkLogin() {
    this.userService.loginUser(undefined, undefined).subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );
    console.log(this.loggedUser);
  }

  test() {
    console.log(this.searchResults); 
   }
  
}
