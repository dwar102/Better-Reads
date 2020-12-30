import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShelfComponent } from './shelf/shelf.component';
import { NavbarComponent } from './navbar/navbar.component'	;
import { UserService } from './services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { UrlService } from './url.service';
import { FormsModule } from '@angular/forms';
import { AddmediaComponent } from './addmedia/addmedia.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { RatingWidgetComponent } from './rating-widget/rating-widget.component';
import { MediaComponent } from './media/media.component';
import { CommentsmediaComponent } from './commentsmedia/commentsmedia.component';
import { AddCommentComponent } from './add-comment/add-comment.component';
import { ViewmediaComponent } from './viewmedia/viewmedia.component';
import { ViewmessagesComponent } from './viewmessages/viewmessages.component';
import { WritemessageComponent } from './writemessage/writemessage.component';
import { SearchComponent} from './search/search.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CommonModule } from '@angular/common';
import { DatePipe } from '@angular/common';
import { FriendshipsComponent } from './friendships/friendships.component';

@NgModule({
  declarations: [
    AppComponent,
	  ShelfComponent,
    NavbarComponent,
    AddmediaComponent,
    ProfileComponent,
    RatingWidgetComponent,
    RegisterComponent,
    ProfileComponent,
    MediaComponent,
    CommentsmediaComponent,
    AddCommentComponent,
    ViewmediaComponent,
    ViewmessagesComponent,
    WritemessageComponent,
    SearchComponent,
    FriendshipsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    FontAwesomeModule,
    CommonModule
  ],
  providers: [
    UserService,
    CookieService,
    UrlService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
