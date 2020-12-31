import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MediaService } from '../services/media.service';

@Component({
  selector: 'app-addmedia',
  templateUrl: './addmedia.component.html',
  styleUrls: ['./addmedia.component.styl']
})
export class AddmediaComponent implements OnInit {
  @Output() addMediaEvent: EventEmitter<any> = new EventEmitter();
  newCreator: String;
  newMediatype: Number;
  newGenre: Number;
  newTitle: String;
  newDate: Date;

  constructor(private mediaService: MediaService) { }

  ngOnInit(): void {
  }

  addMedia(){
    if(this.newDate){
      if(this.newCreator){
        if(this.newTitle){
            if(this.newGenre){
              console.log(this.newCreator)
              console.log(this.newGenre)
              console.log(this.newMediatype)
              console.log(this.newTitle)
                this.mediaService.addMedia(this.newCreator, this.newDate, this.newGenre, 1, this.newTitle).subscribe(
                    resp => {
                      this.addMediaEvent.emit();
                    }
                  );
                  alert("Successfully added")
            }
            else{
              alert("You must enter a genre")
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
    else{
      alert("You must enter a publication date")
    }
  }

 
  displayGenreValue(){
    console.log(this.newGenre)
  }

  displayMediatypeValue(){
    console.log(this.newMediatype)
  }
}
