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
import {TokenStorage} from "../shared/TokenStorage";
import {Interceptor} from "../shared/Interceptor";
import {AuthService} from "../shared/service/AuthService";
import { SignupComponent } from './signup/signup.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import {CategoryService} from "../shared/service/CategoryService";
import { CourseListComponent } from './course-list/course-list.component';
import { CourseFormComponent } from './course-form/course-form.component';
import {AutoSizeDirective} from "../shared/directive/AutoSizeDirective";
import {TagInputModule} from "ngx-chips";
import { MyCoursesComponent } from './my-courses/my-courses.component';

@NgModule({
  declarations: [
    AppComponent,
    AutoSizeDirective,
    NavbarComponent,
    HomeComponent,
    CourseItemComponent,
    CourseDetailsComponent,
    LoginComponent,
    SignupComponent,
    CourseListComponent,
    CourseFormComponent,
    MyCoursesComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: '', pathMatch: 'full', redirectTo: 'home'},
      {path: "home", component: HomeComponent},
      {path: "course/new", component: CourseFormComponent},
      {path: "course/:id", component: CourseDetailsComponent},
      {path: "courses/all", component: CourseListComponent},
      {path: "courses/:category", component: CourseListComponent},
      {path: "user/courses", component: MyCoursesComponent},
      {path: "login", component: LoginComponent},
      {path: "signup", component: SignupComponent}
    ]),
    TagInputModule,
    ToastrModule.forRoot({
      timeOut: 3000
    })
  ],
  providers: [
    AuthService,
    CategoryService,
    CourseService,
    TokenStorage,
    {provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
