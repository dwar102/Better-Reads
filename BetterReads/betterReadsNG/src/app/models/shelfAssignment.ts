import { Media } from "./media";
import { Shelf } from "./shelf";
import { User } from "./user";

export class ShelfAssignment {
    id: Number;
    name: String;
    user: User;
    shelf: Shelf;
    media: Media;
}