import { Component, Input, OnInit } from '@angular/core';
import { userMessage } from '../models/usermessage';
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-viewmessages',
  templateUrl: './viewmessages.component.html',
  styleUrls: ['./viewmessages.component.styl']
})
export class ViewmessagesComponent implements OnInit {
  Messages: userMessage[];
  @Input() loggedUser;

  constructor(private messageService:MessageService) { }

  ngOnInit(): void {
    this.messageService.getMessages(this.loggedUser.id).subscribe(
      resp=> {
        this.Messages = resp;
      }
    );
  }
}
