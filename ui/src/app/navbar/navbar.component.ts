import {Component, OnInit} from '@angular/core';
import {TokenStorage} from "../../shared/authentication/TokenStorage";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private tokenStorage : TokenStorage, private router: Router) { }

  ngOnInit() {
  }

  isAuthenticated() {
    if (this.tokenStorage.getToken() != null) {
      return true;
    }
    return false;
  }

  logOut(){
    this.tokenStorage.removeToken();
    this.router.navigate(['login'])
  }

}
