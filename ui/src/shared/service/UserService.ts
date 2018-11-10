import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

  private apiRoot : string = "http://localhost:8080/users"

  constructor(private http: HttpClient){
  }

  getCurrentUser() : Observable<any> {
    return this.http.get(this.apiRoot + "/current");
  }

  findAllCourses() : Observable<any> {
    return this.http.get(this.apiRoot + "/courses");
  }

  assignCourse(courseId : number) : Observable<any> {
    return this.http.put(this.apiRoot + "/courses/" + courseId, null);
  }
}
