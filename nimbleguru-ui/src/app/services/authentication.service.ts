import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Cookie } from 'ng2-cookies';

export class User{
  constructor(
    public status:string,
     ) {}
  
}

export class JwtResponse{
  constructor(
    public jwttoken:string,
     ) {}
  
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpClient:HttpClient
  ) { 
     }

     authenticate(email, password) {
      return this.httpClient.post<any>('http://localhost:8080/authentication-service/authenticate',{email,password}).pipe(
       map(
         userData => {
          sessionStorage.setItem('email',email);
          sessionStorage.setItem('role',userData.token[1]);
          let tokenStr= 'Bearer '+userData.token[2];
          sessionStorage.setItem('token', tokenStr);
          return userData;
         }
       )
  
      );
    }
  isUserLoggedIn() {
    let user = sessionStorage.getItem('email')
    //console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('email');
    Cookie.delete('email')
  }
}
