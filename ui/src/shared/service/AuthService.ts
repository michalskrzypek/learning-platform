import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";

const generateTokenUrl: string = "http://localhost:8080/token/generate-token"

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    const credentials = {username: username, password: password};
    return this.http.post(generateTokenUrl, credentials);
  }

}
