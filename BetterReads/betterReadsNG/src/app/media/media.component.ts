import { Component, OnInit, Output, Input  } from '@angular/core';
import { User } from '../models/user';

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.styl']
})
export class MediaComponent implements OnInit {
  @Input() public loggedUser: User; 
  constructor() { }

  ngOnInit(): void {
  }

}