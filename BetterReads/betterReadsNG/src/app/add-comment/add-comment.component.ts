import { Component, Input, OnInit } from '@angular/core';
import { mediaComment } from '../models/mediacomment';
import { CommentsmediaService } from '../services/commentsmedia.service';
import { Router } from '@angular/router';
import { Media } from '../models/media';
import { User } from '../models/user';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.styl']
})
export class AddCommentComponent implements OnInit {
message: string;
newComment: mediaComment;
@Input() public loggedUser: User;
@Input() public media: Media;
constructor(private commentService:CommentsmediaService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.newComment = new mediaComment;
    this.newComment.message = this.message;
    this.newComment.media= new Media;
    this.newComment.media.id= 2; // Hardcoded for testing, will be retrieved from button onClick in final state
    console.log(this.loggedUser);
    this.newComment.user = new User;
    this.newComment.user.id = 62; // Not sure how to access this.loggedUser.id at the moment
    this.commentService.placeMediaComment(this.newComment).subscribe();
  }    

}