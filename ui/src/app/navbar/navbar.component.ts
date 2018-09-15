import {Component, OnInit} from '@angular/core';
import {TokenStorage} from "../../shared/TokenStorage";
import {Router} from "@angular/router";
import {CategoryService} from "../../shared/service/CategoryService";
import {Category} from "../../shared/model/Category";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  categories: Category[];

  constructor(private tokenStorage: TokenStorage, private router: Router, private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.categoryService.findAll("count", true).subscribe(data =>
      this.categories = data)
  }

  isAuthenticated() {
    if (this.tokenStorage.getToken() != null) {
      return true;
    }
    return false;
  }

  logOut() {
    this.tokenStorage.removeToken();
    this.router.navigate(['login'], {queryParams: {logout: true}})
  }

}
