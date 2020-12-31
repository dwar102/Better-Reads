import { Component, OnChanges, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { User } from '../models/user';
import { Media } from '../models/media';
import { Shelf } from '../models/shelf';
import { ShelfAssignment } from '../models/shelfAssignment';

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
  siblings = new Array<string>('Jane', 'Jack', 'Sophie');

  constructor(private router: Router, private shelfService: ShelfService) { }//  constructor(private mediaService: MediaService) { }

  ngOnChanges(): void {
    console.log("count is: {{this.count}}");
  }
  ngOnInit(): void {
    this.count = 0;
    this.getShelves();
  }
  getShelves() {
    console.log(this.loggedUser);
    this.shelfService.getUserShelves(this.loggedUser.id).subscribe(
      resp => {
        // console.log("got a response: " + resp);
        // this.shelfAssignments = resp;
        // this.count = this.shelfAssignments.length;
        this.userShelves = resp;
        // for (var i = 0; i < 1/*this.userShelves.length*/; i++) {
        // console.log("inside for loop: " + this.userShelves[i].id);
        //1. run getshelfassignments
        //this.getShelfAssignments(this.userShelves[i].id);
        this.shelfService.getShelfAssignments(this.userShelves[0].id).subscribe(
          resp => {

            //2. assign count for each shelf
            console.log(`got a response (an array with ${resp.length} items)`);
            // this.shelfAssignments = resp;
            // this.shelfAssignments.forEach(function (s) {
            //   console.log(s);
            // }); 

            this.userShelves[0].count = resp.length;
            this.userShelves[0].shelfAssignments = resp;
          }
        );
        // }
      }
    );
  }
  // addShelfAssignment(sa : ShelfAssignment) {
  //   this.shelfService.addShelfAssignment(sa).subscribe(
  //     resp => {
  //     }
  //   );
  // }

}
