import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserTag } from '../models/usertag';

@Injectable({
  providedIn: 'root'
})
export class UsertagService {
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type':'application/json'})

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  addTag(name: String, mid: Number, uid: Number){
    const addtagurl = 'http://localhost:8080/BetterReads/usertag/add?' + 'name=' + name + '&media_id=' + mid
                      +  '&user_id=' + uid;
     console.log(addtagurl);
    return this.http.post(addtagurl, {/*headers:this.formHeaders, withCredentials:true*/}).pipe(
      map(resp => resp as UserTag)
    );
  }
}
