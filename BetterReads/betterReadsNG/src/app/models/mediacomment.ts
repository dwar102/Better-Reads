import { Media } from "./media";
import { User } from "./user";

export class mediaComment {
    id: number;
    comment_date: Date;
    media: Media;
    parent: mediaComment;
    user: User;
    message: String;
}