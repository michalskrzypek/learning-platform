import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

  private apiRoot : string = "http://localhost:8080"

  constructor(private http: HttpClient){
  }

  findAllCourses() : Observable<any> {
    return this.http.get(this.apiRoot + "/my-courses");
  }

  assignCourse(courseId : number) : Observable<any> {
    return this.http.post(this.apiRoot + "/my-courses/add/" + courseId, null);
  }
}
