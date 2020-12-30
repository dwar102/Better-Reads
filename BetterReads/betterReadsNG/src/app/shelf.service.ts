import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Media } from './models/media';
import { Shelf } from './models/shelf';
// import { MediaType } from '../models/mediatype';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ShelfAssignment } from './models/shelfAssignment';

@Injectable({
  providedIn: 'root'
})
export class ShelfService {
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type':'application/json'})
  constructor(private http: HttpClient, private cookieService: CookieService) { }
/*    getUsers(): Observable<User[]> {
        return this.http.get(this.URL)
            .map((response:Response) => response.json())
                .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
    }*/

  getUserShelves(userId : Number): Observable<Shelf[]>{
    // console.log(media);

    const addmediaurl = 'http://localhost:8080/BetterReads/shelves?user=' + userId;
    // console.log(addmediaurl);
    return this.http.get(addmediaurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as Shelf[])
    );
  }
  getShelfAssignments(shelfId : Number): Observable<Media[]>{
    // console.log(media);

    const addmediaurl = 'http://localhost:8080/BetterReads/shelves/assignments/?shelf=' + shelfId;
    // console.log(addmediaurl);
    return this.http.get(addmediaurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as Media[])
    );
  }
  addShelfAssignment(sa: ShelfAssignment): Observable<ShelfAssignment>{
    const addmediaurl = 'http://localhost:8080/BetterReads/shelves/assignments/add';
    return this.http.post(addmediaurl, sa).pipe(
      map(resp => resp as ShelfAssignment)
    );
  }
  removeShelfAssignment(assgn: ShelfAssignment): Observable<ShelfAssignment>{
    const addmediaurl = 'http://localhost:8080/BetterReads/shelves/assignments?assgn=' + assgn.id;
    return this.http.delete(addmediaurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as ShelfAssignment)
    );
  }


  // addMedia(newCreator: String, newDate: Date, newGenre: Number, newMediaType: Number, newTitle: String): Observable<Media>{
  //   let genre = new Genre();
  //   genre.id = newGenre;
  //   let mediaType = new MediaType();
  //   mediaType.id = newMediaType;
  //   let media = new Media();
  //   media.title = newTitle;
  //   media.creator = newCreator;
  //   media.genre = genre;
  //   media.mediaType = mediaType;
  //   media.publicationDate = newDate;
  //   // console.log(media);

  //   const addmediaurl = 'http://localhost:8080/BetterReads/media/add?' + 'creator=' + newCreator + '&date=' + newDate
  //                     +  '&genre=' + newGenre + '&mediatype=' + newMediaType + '&title=' + newTitle;
  //   // console.log(addmediaurl);
  //   return this.http.post(addmediaurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
  //     map(resp => resp as Media)
  //   );
  // }

}



