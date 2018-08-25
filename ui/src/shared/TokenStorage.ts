import { Injectable } from '@angular/core';


const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage {

  constructor() { }

  removeToken() {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.clear();
  }

  saveToken(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY,  token);
  }

  getToken(): string {
    return window.localStorage.getItem(TOKEN_KEY);
  }
}
