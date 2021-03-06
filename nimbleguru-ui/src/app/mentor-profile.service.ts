import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {Mentor } from './models/mentor.model'
import { FAQ } from './models/faq.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Cookie } from 'ng2-cookies';
import { Session } from './models/session.model';


const url = 'http://localhost:8080/mentor-profile-service/mentor'
@Injectable({
  providedIn: 'root'
})
export class MentorProfileService {
  mentorEmail:any;
  sessionId:any;
  constructor(private http : HttpClient,public snackBar : MatSnackBar) { }
  public registerFromRemote(user:Mentor):Observable<any>{
    return this.http.post<any>("http://localhost:8080/mentor-profile-service/register",Mentor);
  }
  getAll() :Observable<any>{
    return this.http.get<any>(url);
  }
  getByEmail(email :String ) : Observable<any>{
    return this.http.get(`http://localhost:8080/mentor-profile-service/mentor/${email}`);
  }
  getSessionByEmail(email :String ) : Observable<any>{
    return this.http.get(`http://localhost:8080/mentor-profile-service/sessions/${email}`);
  }
  updateMentor(mentor : Mentor) : Observable<any>
  {
    return this.http.put("http://localhost:8080/mentor-profile-service/updateProfile",mentor);
  }
  saveFaq(faq : FAQ) : Observable<any>
  {
    return this.http.post<any>("http://localhost:8080/mentor-platform-service/register",faq);
  }
  saveMentor(mentor1 : Mentor) : Observable<any>
  {
    return this.http.post("http://localhost:8080/mentor-profile-service/register",mentor1);
  }
  setQuerySubject(session: Session): Observable<any>{
    return this.http.post("http://localhost:8080/mentor-platform-service/query",session);
  }
  sucess(msg)
  {
    this.snackBar.open(msg,'');
  }
  setMentorEmail(mentorEmail:any){
    Cookie.set('mentorEmail' , mentorEmail )
    return this.mentorEmail =mentorEmail;
  }
  getMentorEmail(){
    return Cookie.get('mentorEmail');
  }
  setSessionId(sessionId:any){
    Cookie.set('sessionId', sessionId)
    return this.sessionId=sessionId;
  }
  getSessionId(){
    return Cookie.get('sessionId')
  }

}
