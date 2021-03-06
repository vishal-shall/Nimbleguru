import { HttpClient } from '@angular/common/http';
import { Injectable, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Observable } from 'rxjs';
import { LoginModel } from './models/login.model';
import { RegisterModel } from './models/register.model';

const url = 'http://localhost:8080/student-profile-service/students'

@Injectable({
  providedIn: 'root'
})
export class StudentProfileService {
  email: String;
  role:String;
  subject: any;
  constructor(private http: HttpClient, private router:Router) { }

  public registerFromRemote(user: RegisterModel): Observable<any>{
    //return this.http.post<any>('http://localhost:8086/registration', user);
    return this.http.post<any>('http://localhost:8080/student-profile-service/registration', user);

  }
  public logInFromRemote(user: LoginModel): Observable<any>{
    return this.http.post<any>('http://localhost:8080/student-profile-service/register', user);
  }
  getAll(): Observable<any>{
    return this.http.get<any>(url);
  }
  getByEmail(email: String ): Observable<any>{
    return this.http.get(`http://localhost:8080/student-profile-service/students/${email}`);
  }
  getuser(): Observable<any>{
    return this.http.get('http://localhost:8080/student-profile-service/registration');
  }
  getBySubject(subject: String): Observable<any>{
    return this.http.get(`http://localhost:8080/mentor-profile-service/mentors/${subject}`);
  }
  getFaqBySubject(subject:String): Observable<any>{
    return this.http.get(`http://localhost:8080/mentor-platform-service/faq/${subject}`);
  }
  getSessionByEmail(email:String): Observable<any>{
    return this.http.get(`http://localhost:8080/student-profile-service/sessions/${email}`);
  }

  emailService(email: any){
    Cookie.set('email', email)
    return this.email = email;
  }
  abc(){
    return Cookie.get('email');
  }
  setRole(role:any){
    Cookie.set('role' , role )
    return this.role = role;
  }
  getRole(){
    return Cookie.get('role');
  }
  // Logout(){
  //   Cookie.deleteAll()
  //   sessionStorage.clear();
  //   this.router.navigate['/']
  // }
  setSubject(subject: any){
    this.subject=subject;
  }
  getSubject(){
    return this.subject;
  }
}
