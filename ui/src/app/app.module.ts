import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {RouterModule} from "@angular/router";
import {HomeComponent} from './home/home.component';
import {CourseItemComponent} from './course-item/course-item.component';
import {CourseDetailsComponent} from './course-details/course-details.component';
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {CourseService} from "../shared/service/CourseService";
import {LoginComponent} from './login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenStorage} from "../shared/authentication/TokenStorage";
import {Interceptor} from "../shared/authentication/Interceptor";
import {AuthService} from "../shared/service/AuthService";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    CourseItemComponent,
    CourseDetailsComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    BrowserModule,
    RouterModule.forRoot([
      {path: '', pathMatch: 'full', redirectTo: 'home'},
      {path: "home", component: HomeComponent},
      {path: "course/:id", component: CourseDetailsComponent},
      {path: "login", component: LoginComponent}
    ])
  ],
  providers: [
    CourseService,
    TokenStorage,
    AuthService,
    {provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
