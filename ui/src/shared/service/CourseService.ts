import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Course} from "../model/Course";
import {Pager} from "../model/Pager";

@Injectable()
export class CourseService {

  private apiRoot : string = "http://localhost:8080/courses"

  constructor(private http: HttpClient){
  }

  save(course: Course) : Observable<any> {
    return this.http.post(this.apiRoot, course);
  }

  findAllByCategory(category: string, pager: Pager) : Observable<any>{
    let params = new HttpParams().append('page', pager.number.toString()).append('size', pager.size.toString());
    if(pager.sortBy != null){
      params.append('sort', pager.sortBy);
    }
    return this.http.get(this.apiRoot + '/' + category, {params : params});
  }

  findAll(pager: Pager) : Observable<any>{
    let params = new HttpParams().append('page', pager.number.toString()).append('size', pager.size.toString());
    if(pager.sortBy != null){
      params.append('sort', pager.sortBy);
    }
    return this.http.get(this.apiRoot, {params : params});
  }

  delete(courseId: number): Observable<any>{
    return this.http.delete(this.apiRoot + "/" + courseId);
  }

}
