import { Injectable } from '@angular/core';
import { Genre } from '../models/genre';
import { Media } from '../models/media';
import { MediaType } from '../models/mediatype';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  constructor() { }

addMedia(newCreator: String, newGenre: Number, newMediaType: Number, newTitle: String){
  let genre = new Genre();
  genre.id = newGenre;
  let mediaType = new MediaType();
  mediaType.id = newMediaType;
  let media = new Media();
  media.title = newTitle;
  media.creator = newCreator;
  media.genre = genre;
  media.mediaType = mediaType;
  console.log(media);
}

}
