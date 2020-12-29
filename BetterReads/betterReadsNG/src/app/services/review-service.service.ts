import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlService } from '../url.service';
import { Observable, pipe } from 'rxjs';
import { map } from 'rxjs/operators';
import { Review } from '../models/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewServiceService {
reviewUrl: string;
private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
'Content-Type': 'application/x-www-form-urlencoded'});
private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
'Content-Type':'application/json'});
  constructor(private http: HttpClient, private urlService: UrlService, private cookieService: CookieService) { 
    this.reviewUrl = this.urlService.getUrl() + 'reviews';
  }
  getReviews(id: Number): Observable<Review[]> {
    return this.http.get(this.reviewUrl + id, {withCredentials:true}).pipe(
      map(resp => resp as Review[])
    );
  }
  placeReview(review: Review): Observable<Review> {
    return this.http.post(this.reviewUrl, review, {headers: this.regHeaders, withCredentials:true}).pipe(
      map(resp => resp as Review)
    );
  }
}
