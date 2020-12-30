import { Component, OnInit, Output, Input  } from '@angular/core';
import { Media } from '../models/media';
import { User } from '../models/user';
import { ShelfAssignment } from '../models/shelfAssignment';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import { ShelfService } from '../shelf.service'
import { Router } from '@angular/router';
@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.styl']
})
export class MediaComponent implements OnInit {
  faTrash = faTrash;
  faPlusSquare = faPlusSquare;
  @Input() public loggedUser: User; 
  @Input() public items: Media[];
  constructor(private shelfService: ShelfService) { }

  ngOnInit(): void {
  }
  removeShelfAssignment(sa : ShelfAssignment) {
    //alert("removeShelfAssignment: " + sa.id + " " + sa.name + " " + sa.shelf.id + " " + sa.user.username);
    this.shelfService.removeShelfAssignment(sa).subscribe(
      resp => {
        console.log("before delete");
        console.log(this.items);
        console.log("items length: " + this.items.length); 
        for(var i = 0; i < this.items.length; i++){
          if(this.items[0].id == sa.id){
            this.items.splice(i,1);
          }
        }
        console.log("after delete");
        console.log(this.items);
        console.log("items length: " + this.items.length); 
      }
    );
  }
}
