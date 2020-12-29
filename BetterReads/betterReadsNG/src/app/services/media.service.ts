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
    // console.log(media);
    const addmediaurl = 'http://localhost:8080/BetterReads/media/add?' + 'creator=' + newCreator + '&date=' + newDate
                      +  '&genre=' + newGenre + '&mediatype=' + newMediaType + '&title=' + newTitle;
    // console.log(addmediaurl);
    return this.http.post(addmediaurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as Media)
    );
  }

  getMedia(id: Number){
    const viewurl = 'http://localhost:8080/BetterReads/media/' + id;
    return this.http.get(viewurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as Media)
    );
  }

  getNumRatings(id: Number){
    const url = 'http://localhost:8080/BetterReads/media/totalratings?media_id=' + id;
    return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as number)
    );
  }

  getAvgRating(id: Number){
    const url = 'http://localhost:8080/BetterReads/media/avgrating?media_id=' + id;
    return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as number)
    );
  }

  getTagCount(id: Number){
    const url = 'http://localhost:8080/BetterReads/media/tagcount?media_id=' + id;
    return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as number[])
    );
  }

  getTagNames(id: Number){
    const url = 'http://localhost:8080/BetterReads/media/tagnames?media_id=' + id;
    return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as String[])
    );
  }

  getSearchResults(searchType: string, searchContent: string) {
    const url = 'http://localhost:8080/BetterReads/media/search?type=' + searchType + '&content=' + searchContent;
    return this.http.put(url, {headers:this.formHeaders, withCredentials:true}).pipe(
      map(resp => resp as String[])
    );
  }

}
