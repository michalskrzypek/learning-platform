import { Component, OnInit } from '@angular/core';
import {TokenStorage} from "../../shared/authentication/TokenStorage";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private tokenStorage : TokenStorage) { }

  ngOnInit() {
  }

  isAuthenticated() {
    if (this.tokenStorage.getToken() != null) {
      return true;
    }
    return false;
  }

}
