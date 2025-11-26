import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly TOKEN_KEY = 'auth_token';
  private readonly USER_KEY = 'auth_user';
  private readonly VALID_USER = { username: 'Admin', password: '123' };
  login(username: string, password: string): boolean {
    if (username === this.VALID_USER.username && password ===
      this.VALID_USER.password) {
      localStorage.setItem(this.TOKEN_KEY, 'fake-jwt-token');
      localStorage.setItem(this.USER_KEY, username);
      return true;
    }
    this.logout();
    return false;
  }
  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.TOKEN_KEY);
  }
  getUser(): string | null {
    return localStorage.getItem(this.USER_KEY);
  }
  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
  }
}
