import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { FeedbackService } from '../feedback.service';
import { MentorProfileService } from '../mentor-profile.service';
import { MentorProfileComponent } from '../mentor-profile/mentor-profile.component';
import { Feedback } from '../models/feedback.model';
import { SessionService } from '../session.service';
import { StudentProfileService } from '../student-profile.service';
import { MatMenuModule} from '@angular/material/menu';
@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  name = this.studentProfileService.abc().split('@')[0].toUpperCase();

  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number;
  feedback: Feedback = new Feedback();
  feedbackForm: FormGroup;
  rating: number;
  avatar=this.name.charAt(0).toUpperCase();
  sessionId:String;
  // email:String = this.studentProfileService.abc();
  // email:String = "khans12311e23d12ka35678527@gmail.com";
  session:any;
  constructor(private _route:ActivatedRoute,private mps:MentorProfileService ,private ss: SessionService , private Fservice: FeedbackService, private formBuilder: FormBuilder, private studentProfileService: StudentProfileService, private router:Router) {
    this._route.params.subscribe(params => {
      console.log("params" , params["sid"])
      this.sessionId = params["sid"];
    })
   }

  ngOnInit() {
    this.feedbackForm = this.formBuilder.group({
      'comment': [this.feedback.comment]
    });
    // this.feedback.email = "vishalkapoor1@gmail.com";
    let abc = this.ss.getSessionbyID(this.sessionId).subscribe(data=>{this.session=data;
      this.feedback.email = this.session.teacherEmail;
      console.log(this.feedback.email);
    });
  }


  countStar(star) {
    this.selectedValue = star;
    console.log('Value of star', star);
    console.log(star)
    this.feedback.rating = star;
    

  }


  onSubmit() {
    this.Fservice.saveFeedback(this.feedback).subscribe(data => this.feedback = data);
    this.router.navigate(['/student-profile']);

  }
  Logout(){
    Cookie.deleteAll()
    sessionStorage.clear();
  }
}
