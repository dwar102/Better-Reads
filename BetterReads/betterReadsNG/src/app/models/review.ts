import { Media } from "./media";
import { User } from "./user";

export class Review {
    id: Number;
    date: Date;
    rating: Number;
    user: User;
    media: Media;
    
}