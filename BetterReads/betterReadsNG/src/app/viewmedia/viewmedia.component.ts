import { Component, Input, OnInit } from '@angular/core';
import { MediaService } from '../services/media.service';

@Component({
  selector: 'app-viewmedia',
  templateUrl: './viewmedia.component.html',
  styleUrls: ['./viewmedia.component.styl']
})
export class ViewmediaComponent implements OnInit {
  
  @Input() mediaId: Number;

  constructor(private mediaService: MediaService) { }

  ngOnInit(): void {
    this.viewMedia();
  }

  viewMedia(){
    let m = this.mediaService.getMedia(this.mediaId);
    let numRatings = this.mediaService.getNumRatings(this.mediaId);
    let avgRating = this.mediaService.getAvgRating(this.mediaId);
    let tagCounts = this.mediaService.getTagCount(this.mediaId);
    let tagNames = this.mediaService.getTagNames(this.mediaId);


  }





}
