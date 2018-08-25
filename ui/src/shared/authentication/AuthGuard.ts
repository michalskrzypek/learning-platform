import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {TokenStorage} from "./authentication/TokenStorage";
import {ToastrService} from "ngx-toastr";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private tokenStorage: TokenStorage, private toastr: ToastrService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.tokenStorage.getToken() != null) {
      return true;
    }
    this.toastr.info("You must be logged in to see the content!");
    return false;
  }


}
