// import { Injectable } from '@angular/core';
// import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
// import { AuthenticationService } from './authentication.service';

// @Injectable({
//   providedIn: 'root'
// })
// export class BasicAuthHtppInterceptorService implements HttpInterceptor {

//   constructor() { }

//   // intercept(req: HttpRequest<any>, next: HttpHandler) {
//   //   console.log(sessionStorage.getItem('token'))
//   //   console.log(sessionStorage.getItem('email'))
//   //   if (sessionStorage.getItem('email') && sessionStorage.getItem('token')) {
//   //     req = req.clone({
//   //       setHeaders: {
//   //         authorization: sessionStorage.getItem('token')
//   //       }
//   //     })
//   //     console.log("req")
//   //     console.log(req.headers)
//   //   }
    
//   //   return next.handle(req);
//   //   }
// }