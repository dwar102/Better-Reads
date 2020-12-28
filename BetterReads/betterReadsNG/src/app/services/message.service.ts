import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlService } from '../url.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { userMessage } from '../models/usermessage';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  userMessagesUrl: string;
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'), 
  'Content-Type':'application/json'});

  constructor(private http: HttpClient, private urlService: UrlService, private cookieService: CookieService) { 
    this.userMessagesUrl = this.urlService.getUrl() + 'messages/'
  }
  getMessages(id: Number): Observable<userMessage[]> {
    return this.http.get(this.userMessagesUrl + id, {withCredentials:true}).pipe(
      map(resp => resp as userMessage[])
    );
  }
  writeMessage(Message: userMessage): Observable<object> {
    return this.http.post(this.userMessagesUrl, Message, {headers: this.regHeaders, withCredentials:true}).pipe(
      map(resp => resp as userMessage)
    )
  }
}
