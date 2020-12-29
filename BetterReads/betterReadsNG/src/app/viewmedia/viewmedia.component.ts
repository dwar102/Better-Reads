import { Component, Input, OnInit } from '@angular/core';
import { Media } from '../models/media';
import { User } from '../models/user';
import { MediaService } from '../services/media.service';

@Component({
  selector: 'app-viewmedia',
  templateUrl: './viewmedia.component.html',
  styleUrls: ['./viewmedia.component.styl']
})
export class ViewmediaComponent implements OnInit {
  @Input() public loggedUser: User;
  //@Input() mediaId: Number;
  mediaId = 3;
  public numRatings: Number;
  public avgRating: Number;
  public tagCounts: Number[];
  public tagNames: String[];
  public media: Media;

  constructor(private mediaService: MediaService) { }

  ngOnInit(): void {
    this.viewMedia();
  }

  async viewMedia(){
    this.mediaService.getMedia(this.mediaId).subscribe( resp => {this.media = resp; console.log(this.media); 
      console.log(this.media.date);});
    await this.mediaService.getNumRatings(this.mediaId).subscribe( async resp => {this.numRatings = await resp});
    this.mediaService.getAvgRating(this.mediaId).subscribe( resp => {this.avgRating = resp; 
      console.log(resp); 
      console.log(this.avgRating)});
    this.mediaService.getTagCount(this.mediaId).subscribe( resp => {this.tagCounts = resp});
    this.mediaService.getTagNames(this.mediaId).subscribe( resp => {this.tagNames = resp});

   // console.log(await this.avgRating)
   // await console.log(this.numRatings)
  }





}
