import { Component, OnChanges, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { MediaService } from '../services/media.service';
import { Media } from '../models/media';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl']
})
export class SearchComponent implements OnInit {
  @Output() searchEvent: EventEmitter<any> = new EventEmitter();
  @Output() newSearchEvent: EventEmitter<any> = new EventEmitter();
  @Input() public loggedUser: User; 
  public searchContent: string;
  public searchActivated: boolean = false;
  public showResults: boolean = false;
  public basicSearch: boolean = false;
  public advancedSearch: boolean = false;
  public searchResults: Media[];
  public mediaView: boolean = false;
  public mediaViewId: Number;
  public searchType = 'title';
  public minDate: Date;
  public maxDate: Date;
  public minAvgRating: number;
  public genre: number;
  public includeTag: string;
  public excludeTag: string;
  faSearch = faSearch;

  constructor(private userService: UserService, private mediaService: MediaService, private router: Router) { }

  ngOnInit(): void {
    this.basicSearch = true;
  }

  setType(type: string) {
    this.searchType = type;
  }

  search() {
    if (this.basicSearch) {
      this.mediaService.getSearchResults(this.searchType, this.searchContent).subscribe(
        resp => {
          this.searchResults = resp;
          console.log(this.searchResults);
          this.searchEvent.emit();
          this.searchActivated = true;
          this.showResults = true;
        }
      );
    } else {
      /*this.mediaService.filteredSearch(this.minDate, this.maxDate, this.minAvgRating, this.genre, this.includeTag, this.excludeTag).subscribe(
        resp => {
          this.searchResults = resp;
          console.log(this.searchResults);
          this.searchEvent.emit();
          this.searchActivated = true;
          this.showResults;
        }
      );*/
    }
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
    let baseElement = document.getElementById('results');
    let list = document.createElement("ul");

    this.searchResults.forEach((result) => {
      let listItem = document.createElement("li");
      listItem.innerHTML = `Title:  ${result.title}<br>
        Creator: ${result.creator}<br>
        Type:    ${result.mediaType.name}<br>
        Genre:   ${result.genre.name}<br>
        <button type="button" id="${result.id}" >View</button>
      `;
      list.appendChild(listItem);
    });

    baseElement.appendChild(list);

    this.searchResults.forEach((result) => {
      document.getElementById(`${result.id}`).addEventListener('click', function() {
        
      }
    )});
  }

  viewMedia(id: Number) {
    this.mediaViewId = id;
    this.showResults = false;
    this.mediaView = true;
  }

  switchSearchComplexity() {
    if (this.basicSearch) {
      this.basicSearch = false;
      this.advancedSearch = true;
    } else {
      this.advancedSearch = false;
      this.basicSearch = true;
    }
  }

  reset() {
    this.mediaView = false;
    this.searchActivated = false;
    this.showResults = false;
    this.newSearchEvent.emit();
  }
  
}
