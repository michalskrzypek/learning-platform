import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CategoryService {
  apiRoot : string = "http://localhost:8080/categories";

  constructor(private http: HttpClient){
  }

  findAll(sortProperty: string, desc: boolean) : Observable<any>{
    return this.http.get(this.apiRoot + "?sort="+sortProperty + "&desc="+desc);
  }
}
