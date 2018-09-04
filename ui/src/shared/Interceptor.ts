import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {Router} from '@angular/router';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Observable} from "rxjs/Observable";
import {TokenStorage} from "./TokenStorage";
import {ToastrService} from "ngx-toastr";
import {ErrorDetails} from "./model/ErrorDetails";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  errorDetails: ErrorDetails;

  constructor(private token: TokenStorage, private router: Router, private toastr: ToastrService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authReq = req;
    let userToken: string = this.token.getToken();

    if (userToken != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }

    return next.handle(authReq).do((event: HttpEvent<any>) => {
      if(event instanceof HttpResponse) {
      }},
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          this.errorDetails = err.error;
          if (err.status == 401) {//user unauthorized
            this.token.removeToken();
            this.toastr.show("Log in to see the content.")
            this.router.navigate(['login']);
          } else if (err.status == 403) {//content forbidden
            this.toastr.show("You have unsufficient rights to see the content.")
          } else if (err.status == 400) {//bad request for invalid form completion
            this.toastr.show(this.errorDetails.message);
          }
        }
      }
    )
  }
}
