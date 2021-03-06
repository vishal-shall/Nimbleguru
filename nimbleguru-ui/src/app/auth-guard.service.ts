import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from './auth.service';
@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public auth: AuthService, public router: Router, private snackbar:MatSnackBar) {}
  canActivate(): boolean {
    if (!this.auth.isAuthenticated()) {
      this.snackbar
      .open(("Oops! You can't access that page. You need to login first"), '', {

        verticalPosition: 'top',
        duration:4000
      })
      // .onAction().subscribe(()=>this.router.navigate(['login']))
    

      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}