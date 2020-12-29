import { Genre } from "./genre";
import { MediaType } from "./mediatype";

export class Media {
    id: Number;
    title: String;
    creator: String;
    mediaType: MediaType;
    genre: Genre;
    date: Date;
}