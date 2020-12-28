import { Component, OnChanges, OnInit, EventEmitter, Output, Input} from '@angular/core';
import { mediaComment} from '../models/mediacomment';
import { CommentsmediaService } from '../services/commentsmedia.service';
import { Router } from '@angular/router';
import { Media } from '../models/media';
import { User } from '../models/user';

@Component({
  selector: 'app-commentsmedia',
  templateUrl: './commentsmedia.component.html',
  styleUrls: ['./commentsmedia.component.styl']
})
export class CommentsmediaComponent implements OnInit {
  Comments: mediaComment[];
  media: Media;


  constructor(private commentService:CommentsmediaService) { }

  ngOnInit(): void {
    this.commentService.getMediaComments(this.media.id).subscribe(
      resp => {
        this.Comments = resp;
      }
    );
  //   this.commentService.placeMediaComment(this.comment).subscribe();
 }
}

