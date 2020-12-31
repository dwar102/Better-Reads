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
      map(resp => resp as Media[])
    );
  }

  searchByGenreTagNotTagAvgRatingNumRatingsDates(genre: Number, includeTag: String, excludeTag: String,
    minAvgRating: Number, minNumRatings: Number, minDate: Date, maxDate: Date){
      const url = 'http://localhost:8080/BetterReads/media/searchByGenreTagNotTagAvgRatingNumRatingDates?genre=' + genre + '&tagName=' 
      + includeTag.split(" ").join("%20") + '&notTagName=' + excludeTag.split(" ").join("%20") + '&minAvgRating=' + minAvgRating + '&minNumRatings=' 
      + minNumRatings + '&minDate='  + minDate + '&maxDate=' + maxDate;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }

  searchByGenreTagNotTagAvgRatingNumRatings(genre: Number, includeTag: String, excludeTag: String,
    minAvgRating: Number, minNumRatings: Number){
      const url = 'http://localhost:8080/BetterReads/media/searchByGenreTagNotTagAvgRatingNumRating?genre=' + genre + '&tagName=' 
      + includeTag.split(" ").join("%20") + '&notTagName=' + excludeTag.split(" ").join("%20") + '&minAvgRating=' + minAvgRating + '&minNumRatings=' 
      + minNumRatings;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }

  searchByTagNotTagAvgRatingNumRatingsDates(includeTag: String, excludeTag: String,
    minAvgRating: Number, minNumRatings: Number, minDate: Date, maxDate: Date){
      const url = 'http://localhost:8080/BetterReads/media/searchByTagNotTagAvgRatingNumRatingDates?tagName=' 
      + includeTag.split(" ").join("%20") + '&notTagName=' + excludeTag.split(" ").join("%20") + '&minAvgRating=' + minAvgRating + '&minNumRatings=' 
      + minNumRatings + '&minDate='  + minDate + '&maxDate=' + maxDate;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }
  
  searchByGenreTagAvgRatingNumRatingsDates(genre: Number, includeTag: String, 
    minAvgRating: Number, minNumRatings: Number, minDate: Date, maxDate: Date){
      const url = 'http://localhost:8080/BetterReads/media/searchByGenreTagAvgRatingNumRatingDates?genre=' + genre + '&tagName=' 
      + includeTag.split(" ").join("%20") + '&minAvgRating=' + minAvgRating + '&minNumRatings=' 
      + minNumRatings + '&minDate='  + minDate + '&maxDate=' + maxDate;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }

  searchByTagNotTagAvgRatingNumRatings(includeTag: String, excludeTag: String,
    minAvgRating: Number, minNumRatings: Number){
      const url = 'http://localhost:8080/BetterReads/media/searchByTagNotTagAvgRatingNumRating?tagName=' 
      + includeTag.split(" ").join("%20") + '&notTagName=' + excludeTag.split(" ").join("%20") + '&minAvgRating=' + minAvgRating + '&minNumRatings=' 
      + minNumRatings;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }
  
  searchByGenreAvgRatingNumRatingsDates(genre: Number, 
    minAvgRating: Number, minNumRatings: Number, minDate: Date, maxDate: Date){
      const url = 'http://localhost:8080/BetterReads/media/searchByGenreAvgRatingNumRatingDates?genre=' + genre + '&minAvgRating=' 
      + minAvgRating + '&minNumRatings='  + minNumRatings + '&minDate='  + minDate + '&maxDate=' + maxDate;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }

  searchByGenreTagAvgRatingNumRatings(genre: Number, includeTag: String,
    minAvgRating: Number, minNumRatings: Number){
      const url = 'http://localhost:8080/BetterReads/media/searchByGenreTagAvgRatingNumRating?genre=' + genre + '&tagName=' 
      + includeTag.split(" ").join("%20") + '&minAvgRating=' + minAvgRating + '&minNumRatings=' 
      + minNumRatings;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }
  
  searchByTagAvgRatingNumRatingsDates(includeTag: String, 
    minAvgRating: Number, minNumRatings: Number, minDate: Date, maxDate: Date){
      const url = 'http://localhost:8080/BetterReads/media/searchByTagAvgRatingNumRatingDates?tagName=' + includeTag + '&minAvgRating=' 
      + minAvgRating + '&minNumRatings='  + minNumRatings + '&minDate='  + minDate + '&maxDate=' + maxDate;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }
  
  searchByTagAvgRatingNumRatings(includeTag: String, 
    minAvgRating: Number, minNumRatings: Number){
      const url = 'http://localhost:8080/BetterReads/media/searchByTagAvgRatingNumRating?tagName=' + includeTag + '&minAvgRating=' 
      + minAvgRating + '&minNumRatings='  + minNumRatings;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );

  }
  
  searchByGenreAvgRatingNumRatings(genre: Number, 
    minAvgRating: Number, minNumRatings: Number){
      const url = 'http://localhost:8080/BetterReads/media/searchByGenreAvgRatingNumRating?genre=' + genre + '&minAvgRating=' 
      + minAvgRating + '&minNumRatings='  + minNumRatings;
      console.log(url);
      return this.http.get(url, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
        map(resp => resp as Media[])
      );
  }

}
