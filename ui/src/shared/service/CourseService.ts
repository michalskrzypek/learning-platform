import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Course} from "../model/Course";

@Injectable()
export class CourseService {

  private apiRoot : string = "http://localhost:8080/courses"

  constructor(private http: HttpClient){
  }

  save(course: Course) : Observable<any> {
    return this.http.post(this.apiRoot, course);
  }

  findAllByCategory(category: string) : Observable<any>{
    return this.http.get(this.apiRoot + '/' + category);
  }

  findAll() : Observable<any>{
    return this.http.get(this.apiRoot + '/all');
  }

}
