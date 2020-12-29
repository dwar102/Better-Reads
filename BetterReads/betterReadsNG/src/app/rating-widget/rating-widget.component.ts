import { Component, OnInit, Input } from '@angular/core';
import { User } from '../models/user';
import { Media } from '../models/media';

@Component({
  selector: 'app-rating-widget',
  templateUrl: './rating-widget.component.html',
  styleUrls: ['./rating-widget.component.styl']
})
export class RatingWidgetComponent implements OnInit {
  @Input() public loggedUser: User; 
  @Input() public media_id: Media; 


  constructor() { }

  ngOnInit(): void {
  }

}
