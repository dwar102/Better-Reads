import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Friendships } from '../models/friendships';
import { User } from '../models/user';
import { FriendshipServiceService } from '../services/friendship-service.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-friendships',
  templateUrl: './friendships.component.html',
  styleUrls: ['./friendships.component.styl']
})
export class FriendshipsComponent implements OnInit {
  target: number;
  FriendshipSet: Friendships[];
  newFriendship: Friendships;
  @Input() loggedUser;

  constructor(private friendService:FriendshipServiceService, private router: Router, private userSerive:UserService) { }

  ngOnInit(): void {
    this.friendService.getFriendships(this.loggedUser.id).subscribe(
      resp=>{
        this.FriendshipSet = resp;
      }
    )
  }
  onSubmit() {
    this.newFriendship = new Friendships;
    this.newFriendship.user_id = this.loggedUser;
    this.newFriendship.friend_id = new User;
    this.newFriendship.friend_id.id = this.target;

    console.log(this.newFriendship);
    this.friendService.addFriendship(this.newFriendship).subscribe();

  }
}
