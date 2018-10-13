import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

  private apiRoot : string = "http://localhost:8080"

  constructor(private http: HttpClient){
  }

  findAllCourses() : Observable<any>{
    return this.http.get(this.apiRoot + "/my-courses");
  }
}
