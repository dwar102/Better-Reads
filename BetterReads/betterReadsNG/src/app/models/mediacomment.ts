import { Media } from "./media";
import { User } from "./user";

export class mediaComment {
    id: Number;
    comment_date: Date;
    media: Media;
    parent: mediaComment;
    user: User;
    message: String;
}