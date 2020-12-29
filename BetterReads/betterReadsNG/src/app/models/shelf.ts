import { Media } from "../models/media";
export class Shelf {
    id: Number;
    name: String;
    count: Number;
    shelfAssignments: Media[];
}