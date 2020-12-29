import { Component, OnInit, Input } from '@angular/core';
import { User } from '../models/user';
import { Media } from '../models/media';
import { Review } from '../models/review';
import { ReviewServiceService } from '../services/review-service.service';

@Component({
  selector: 'app-rating-widget',
  templateUrl: './rating-widget.component.html',
  styleUrls: ['./rating-widget.component.styl']
})
export class RatingWidgetComponent implements OnInit {
  @Input() public loggedUser: User; 
  @Input() public mediaId: Number; 
  rev = new Review();
  user = new User();
  media = new Media();
  constructor(private reviewService: ReviewServiceService) { }

  ngOnInit(): void {
  }
  clickEvent(rating){
    //alert(`rating button pushed: (userId)${this.loggedUser.id} (itemId)${this.mediaId}`);
    // rev = new Review();
      // var user = new User();
      this.user.id = this.loggedUser.id;
     this.rev.user = this.user;
      // var media = new Media();
      this.media.id = this.mediaId;
      this.rev.media = this.media;
    this.rev.rating = rating;
      this.reviewService.placeReview(this.rev).subscribe(resp => {
        console.log("place review response: " + resp);
      });
      /**          this.shelfService.getShelfAssignments(this.userShelves[0].id).subscribe(
            resp => {
              
              //2. assign count for each shelf
              console.log(`got a response (an array with ${resp.length} items)`);
              // this.shelfAssignments = resp;
              // this.shelfAssignments.forEach(function (s) {
              //   console.log(s);
              // }); 

              this.userShelves[0].count = resp.length;
              this.userShelves[0].shelfAssignments = resp;
            }
          ); */
  }
}
