import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlService } from '../url.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Friendships } from '../models/friendships';

@Injectable({
  providedIn: 'root'
})
export class FriendshipServiceService {
  FriendshipsUrl: string;
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'), 
  'Content-Type':'application/json'});
  constructor(private http: HttpClient, private urlService: UrlService, private cookieService: CookieService) {
    this.FriendshipsUrl = this.urlService.getUrl() + 'friendships/'
   }
   getFriendships(id: Number): Observable<Friendships[]> {
     return this.http.get(this.FriendshipsUrl + id, {withCredentials:true}).pipe(
       map(resp => resp as Friendships[])
     );
   }
   addFriendship(Friendship: Friendships): Observable<Friendships> {
     return this.http.post(this.FriendshipsUrl, Friendship, {headers: this.regHeaders, withCredentials:true}).pipe(
       map(resp => resp as Friendships)
     )
   }
}
