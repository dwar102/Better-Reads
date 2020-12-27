import { Injectable } from '@angular/core';
import { Genre } from '../models/genre';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Media } from '../models/media';
import { MediaType } from '../models/mediatype';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MediaService {
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type':'application/json'})

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  addMedia(newCreator: String, newDate: Date, newGenre: Number, newMediaType: Number, newTitle: String): Observable<Media>{
    let genre = new Genre();
    genre.id = newGenre;
    let mediaType = new MediaType();
    mediaType.id = newMediaType;
    let media = new Media();
    media.title = newTitle;
    media.creator = newCreator;
    media.genre = genre;
    media.mediaType = mediaType;
    media.publicationDate = newDate;
    // console.log(media);

    const addmediaurl = 'http://localhost:8080/BetterReads/media/add?' + 'creator=' + newCreator + '&date=' + newDate
                      +  '&genre=' + newGenre + '&mediatype=' + newMediaType + '&title=' + newTitle;
    // console.log(addmediaurl);
    return this.http.post(addmediaurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as Media)
    );
  }
}
