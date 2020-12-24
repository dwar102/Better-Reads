import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-addmedia',
  templateUrl: './addmedia.component.html',
  styleUrls: ['./addmedia.component.styl']
})
export class AddmediaComponent implements OnInit {
  newCreator: string;
  newMediatype: string;
  newGenre: string;
  newTitle: string;

  constructor() { }

  ngOnInit(): void {
  }

  addMedia(){
    if(this.newCreator){
      if(this.newTitle){
        if(this.newMediatype){
          if(this.newGenre){

            // this.mediaService.addMedia(newCreator, newGenre, newMediatype, newTitle);
          }
          else{
            alert("You must enter a genre")
          }
        }
        else{
          alert("You must enter a media type")
        }
      }
      else{
        alert("You must enter a title")
      }
    }
    else{
      alert("You must enter a creator")
    }
  }

}
