import { User } from "./user";
import { Media } from "./media";

export class UserTag {
    id: Number;
    tagName: String;
    media: Media;
    user: User;
}