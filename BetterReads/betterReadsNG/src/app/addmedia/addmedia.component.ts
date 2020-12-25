import { Component, OnInit } from '@angular/core';
import { MediaService } from '../services/media.service';

@Component({
  selector: 'app-addmedia',
  templateUrl: './addmedia.component.html',
  styleUrls: ['./addmedia.component.styl']
})
export class AddmediaComponent implements OnInit {
  newCreator: String;
  newMediatype: Number;
  newGenre: Number;
  newTitle: String;

  constructor(private mediaService: MediaService) { }

  ngOnInit(): void {
  }

  addMedia(){
    if(this.newCreator){
      if(this.newTitle){
        if(this.newMediatype){
          if(this.newGenre){
            console.log(this.newCreator)
            console.log(this.newGenre)
            console.log(this.newMediatype)
            console.log(this.newTitle)
              this.mediaService.addMedia(this.newCreator, this.newGenre, this.newMediatype, this.newTitle);
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

 
  displayGenreValue(){
    console.log(this.newGenre)
  }

  displayMediatypeValue(){
    console.log(this.newMediatype)
  }
}
