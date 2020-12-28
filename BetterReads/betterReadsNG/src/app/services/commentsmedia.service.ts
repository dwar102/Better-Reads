import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlService } from '../url.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { mediaComment } from '../models/mediacomment';


@Injectable({
  providedIn: 'root'
})
export class CommentsmediaService {
  mediaCommentsUrl: string;
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type':'application/json'});

  constructor(private http: HttpClient, private urlService: UrlService, private cookieService: CookieService) {
    this.mediaCommentsUrl = this.urlService.getUrl() + 'media/comments/';
   }

   getMediaComments(id: Number): Observable<mediaComment[]> {
     return this.http.get(this.mediaCommentsUrl + id, {withCredentials:true}).pipe(
       map(resp => resp as mediaComment[])
     );
   }
   placeMediaComment(Comment: mediaComment): Observable<object> {
     return this.http.post(this.mediaCommentsUrl, Comment, {headers: this.regHeaders, withCredentials:true}).pipe(
       map(resp => resp as mediaComment)
     );
   }
}
