import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from './models/feedback.model';
@Injectable({
  providedIn: 'root'
})

export class FeedbackService{
    constructor(private http: HttpClient) { }

    saveFeedback(feedback:Feedback){
        return this.http.patch<Feedback>(`http://localhost:8080/mentor-profile-service/mentors/${feedback.email}/feedback?rating=${feedback.rating}` , feedback )

    }


}