import { Component, OnChanges, OnInit, EventEmitter, Output, Input} from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shelf',
  templateUrl: './shelf.component.html',
  styleUrls: ['./shelf.component.styl']
})
export class ShelfComponent implements OnInit, OnChanges {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  @Input() public loggedUser: User; 
  count: number;

  constructor(private router: Router) { }

  ngOnChanges(): void{
    console.log("count is: {{this.count}}");
  }
  ngOnInit(): void {
    this.count = 0;
  }
}
