import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CourseService {

  apiRoot : string = "http://localhost:8080/courses"

  constructor(private http: HttpClient){
  }

  findAllByCategory(category: string) : Observable<any>{
    return this.http.get(this.apiRoot + '/' + category);
  }

  findAll() : Observable<any>{
    return this.http.get(this.apiRoot + '/all');
  }

}
