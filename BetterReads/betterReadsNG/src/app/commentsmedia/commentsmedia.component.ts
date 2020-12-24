import { Component, OnChanges, OnInit, EventEmitter, Output } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-commentsmedia',
  templateUrl: './commentsmedia.component.html',
  styleUrls: ['./commentsmedia.component.styl']
})
export class CommentsmediaComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
