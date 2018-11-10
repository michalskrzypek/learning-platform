import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {User} from "../model/User";
import {GlobalVariable} from "../util/Globals";
import {LoginUser} from "../model/LoginUser";

const generateTokenURL: string = GlobalVariable.BASE_API_URL + "/token/generate-token"
const usersURL: string = GlobalVariable.BASE_API_URL + "/users"

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(loginUser: LoginUser): Observable<any> {
    return this.http.post(generateTokenURL, loginUser);
  }

  signup(user: User) : Observable<any> {
    return this.http.post(usersURL + "/register", user);
  }

  getCurrentUser() : Observable<any> {
    return this.http.get(usersURL + "/current");
  }
}
