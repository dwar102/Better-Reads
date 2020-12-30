import { Component, OnInit, Output, Input  } from '@angular/core';
import { Media } from '../models/media';
import { User } from '../models/user';
import { ShelfAssignment } from '../models/shelfAssignment';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import { ShelfService } from '../shelf.service'
import { Router } from '@angular/router';
import { mediaComment } from '../models/mediacomment';
import { Shelf } from '../models/shelf';
@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.styl']
})
export class MediaComponent implements OnInit {
  faTrash = faTrash;
  faPlusSquare = faPlusSquare;
  @Input() public loggedUser: User; 
  @Input() public items: ShelfAssignment[];
  @Input() public shelfId: number;
  
  constructor(private shelfService: ShelfService) { }
  addMediaId: number;
  sa : ShelfAssignment;
  addMedia : Media;
  shelf: Shelf;
  
  ngOnInit(): void {
  }

  addShelfAssignment(){
    // alert("want to add media with id: " + this.addMediaId + "to shelf with id: " + this.shelfId);
    this.sa = new ShelfAssignment();
    this.addMedia = new Media();
    this.shelf = new Shelf();
    this.addMedia.id = this.addMediaId;
    this.sa.media = this.addMedia;
    this.shelf.id = this.shelfId;
    this.sa.shelf = this.shelf;
    
    this.shelfService.addShelfAssignment(this.sa).subscribe(
      resp => {
        // console.log("before delete");
        // console.log(this.items);
        // console.log("items length: " + this.items.length); 
        // for(var i = 0; i < this.items.length; i++){
        //   if(this.items[i].id == sa.id){
            var newMedia = resp;
            this.items.push(newMedia);
        //   }
        // }
        // console.log("after delete");
        // console.log(this.items);
        // console.log("items length: " + this.items.length); 
      }
    );
  }
  removeShelfAssignment(sa : ShelfAssignment) {
    //alert("removeShelfAssignment: " + sa.id + " " + sa.nang me + " " + sa.shelf.id + " " + sa.user.username);
    this.shelfService.removeShelfAssignment(sa).subscribe(
      resp => {
        console.log("before delete");
        console.log(this.items);
        console.log("items length: " + this.items.length); 
        for(var i = 0; i < this.items.length; i++){
          if(this.items[i].id == sa.id){
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
