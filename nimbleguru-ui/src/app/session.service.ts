import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Session } from './models/session.model';
@Injectable({
  providedIn: 'root'
})

export class SessionService{
    constructor(private http: HttpClient) { }
    findSession(session :any) : Observable<any>{
        return this.http.post(`http://localhost:8080/mentor-platform-service/session/start`,session);
      }
      endSession(sessionId:any){
       return this.http.post(`http://localhost:8080/mentor-platform-service/session/${sessionId}/end`,sessionId);
      }
      getMessageDetails(sessionId:any){
        return this.http.get(`http://localhost:8080/mentor-platform-service/session/message/${sessionId}`)
      }
      getMentorEmail(teacherEmail:any,sessionStatus:any){
        return this.http.get(`http://localhost:8080/mentor-platform-service/session/mentor/${teacherEmail}/${sessionStatus}`)
      }
      getSessionByEmail(email:any){
        return this.http.get(`http://localhost:8080/mentor-platform-service/sessions/${email}`)
      }
      getSessionbyID(sessionId:any){
        return this.http.get(`http://localhost:8080/mentor-platform-service/session/${sessionId}/feedback`)
      }

    }
