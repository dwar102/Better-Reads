import { Component, OnInit, Output, Input  } from '@angular/core';
import { Media } from '../models/media';
import { User } from '../models/user';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { faPlusSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.styl']
})
export class MediaComponent implements OnInit {
  faTrash = faTrash;
  faPlusSquare = faPlusSquare;
  @Input() public loggedUser: User; 
  @Input() public items: [];
  constructor() { }

  ngOnInit(): void {
  }

}
