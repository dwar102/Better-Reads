import { Component, Input, OnInit } from '@angular/core';
import { mediaComment } from '../models/mediacomment';
import { CommentsmediaService } from '../services/commentsmedia.service';
import { Router } from '@angular/router';
import { Media } from '../models/media';
import { User } from '../models/user';
import { UserService } from '../services/user.service'

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.styl']
})
export class AddCommentComponent implements OnInit {
message: string;
newComment: mediaComment; 
@Input() public loggedUser: User;
@Input() mediaId: Number;
constructor(private commentService:CommentsmediaService, private router: Router, private userService:UserService) { 
  console.log(userService.getLoggedUser())
}

  ngOnInit(): void {
    
  }

  onSubmit() {
    this.newComment = new mediaComment;
    this.newComment.message = this.message;
    this.newComment.media= new Media;
    this.newComment.media.id= this.mediaId;
    console.log(this.loggedUser);
    console.log(this.newComment);
    this.newComment.user = new User;
    this.newComment.user.id = this.loggedUser.id;
    this.commentService.placeMediaComment(this.newComment).subscribe();
  }    

}
