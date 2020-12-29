import { Component, OnChanges, OnInit, EventEmitter, Output, Input} from '@angular/core';
import { User } from '../models/user';
import { Media } from '../models/media';
import { Shelf } from '../models/shelf';
import { ShelfService } from '../shelf.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-shelf',
  templateUrl: './shelf.component.html',
  styleUrls: ['./shelf.component.styl']
})

export class ShelfComponent implements OnInit, OnChanges {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  @Input() public loggedUser: User; 
  count: number;
  shelfAssignments: Media[];
  userShelves: Shelf[];

  constructor(private router: Router, private shelfService: ShelfService){ }//  constructor(private mediaService: MediaService) { }

  ngOnChanges(): void{
    console.log("count is: {{this.count}}");
  }
  ngOnInit(): void {
    this.count = 0;
    this.getShelves();
  }
  getShelves(){
    this.shelfService.getUserShelves(this.loggedUser.id).subscribe(
      resp => {
        // console.log("got a response: " + resp);
        // this.shelfAssignments = resp;
        // this.count = this.shelfAssignments.length;
        this.userShelves = resp;
        for (var i = 0; i < this.userShelves.length; i++) {
          console.log("inside for loop: " + this.userShelves[i].id);
          //1. run getshelfassignments
          //this.getShelfAssignments(this.userShelves[i].id);
          this.shelfService.getShelfAssignments(this.userShelves[i].id).subscribe(
            resp => {
              //2. assign count for each shelf
              
              console.log("got a response: " + resp);
              // this.shelfAssignments = resp;
              // this.shelfAssignments.forEach(function (s) {
              //   console.log(s);
              // }); 
              this.userShelves[i].shelfAssignments = resp;
              this.userShelves[i].count = this.userShelves[i].shelfAssignments.length;
            }
          );
        }
      }
    );
  }
  getShelfAssignments(shelfId : Number){
    this.shelfService.getShelfAssignments(shelfId).subscribe(
      resp => {
        //2. assign count for each shelf

        console.log("got a response: " + resp);
        this.shelfAssignments = resp;
        this.shelfAssignments.forEach(function (s) {
          console.log(s);
        }); 
        this.count = this.shelfAssignments.length;
      }
    );
  } 
}
