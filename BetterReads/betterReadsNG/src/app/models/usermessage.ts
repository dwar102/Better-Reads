import { User } from "./user";

export class userMessage {
    message_id: Number;
    message_date: Date;
    parent: userMessage;
    sender: User;
    recipient: User;
    message: String;
}