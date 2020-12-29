import { Component, Input, OnInit } from '@angular/core';
import { Media } from '../models/media';
import { User } from '../models/user';
import { userMessage } from '../models/usermessage';
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-writemessage',
  templateUrl: './writemessage.component.html',
  styleUrls: ['./writemessage.component.styl']
})
export class WritemessageComponent implements OnInit {
message: string;
target: number;
newMessage: userMessage; 
@Input() public loggedUser: User;

  constructor(private messageService:MessageService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.newMessage = new userMessage;
    this.newMessage.message = this.message;
    this.newMessage.sender = this.loggedUser; 
    this.newMessage.recipient = new User;
    this.newMessage.recipient.id = this.target;
    this.messageService.writeMessage(this.newMessage).subscribe();
  }

}
