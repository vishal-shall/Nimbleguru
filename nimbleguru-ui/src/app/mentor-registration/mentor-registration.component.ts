import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MentorProfileService } from '../mentor-profile.service';
import { Mentor } from '../models/mentor.model';
import { StudentProfileService } from '../student-profile.service';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Cookie } from 'ng2-cookies';
import { parse } from 'path';
@Component({
  selector: 'app-mentor-registration',
  templateUrl: './mentor-registration.component.html',
  styleUrls: ['./mentor-registration.component.css']
})
export class MentorRegistrationComponent implements OnInit {

  mentor : Mentor = new Mentor();
  mentor1 : Mentor = new Mentor();
  updateForm: FormGroup;
  hide = true;
  breakpoint: number;
  subjects: any[] = [ 'Physics', 'Maths', 'English', 'Java', 'Chemistry','Civics', 'Economics' , 'Python' , 'Biology','History' , 'Accounts' ,'Business Administration' , 'Geography', 'Political Science'];
  time11: any[] = ['00:00', '1:00', '2:00', '3:00', '4:00', '5:00', '6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'];
  myDate = new Date();
  constructor(private snackbar : MatSnackBar,private formBuilder: FormBuilder, private service1:StudentProfileService,private service:MentorProfileService,private router: Router,private datePipe: DatePipe) { }
  email1 :String = this.service1.abc();
  ngOnInit(): void
  {
    this.service.getByEmail(this.email1).subscribe
    {
      (data => this.mentor1 = data);
      console.log(this.mentor1);
      if(this.mentor.name == null)
      {
        console.log("sgdhf");
      }
    }

    console.log(this.mentor1);
    this.updateForm = this.formBuilder.group({
      name: [this.mentor.name, [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]+ [a-zA-Z]+$/)
      ]],
      email :[this.email1,[
        Validators.required
      ]],
      phoneNumber: [this.mentor.phoneNumber, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(10),
        Validators.pattern(/^[0-9]\d*$/)
      ]],
      subject: [this.mentor.subject, [
        Validators.required,
      ]],
      qualification: [this.mentor.qualification,
         [Validators.required,
      ]],
      startTime : [this.mentor.time,[Validators.required]],
      endTime :[this.mentor.time,[Validators.required,]]
    });
    this.breakpoint = (window.innerWidth <= 600) ? 1 : 2;

}
  OnSave()
  {
      const mentor2: Mentor = {
        // id: 4645,
        
        name: this.updateForm.value.name,
        phoneNumber: this.updateForm.value.phoneNumber,
        email: this.updateForm.value.email,
        qualification: this.updateForm.value.qualification,
        subject: this.updateForm.value.subject,
        status:this.updateForm.value.status,
        joining_date:this.datePipe.transform(this.myDate, 'dd MMM, yyyy'),
        comment:null,
        sessions:this.updateForm.value.sessions,
        time:[
          {
            "startTime": this.updateForm.value.startTime.split(':')[0],
            "endTime": this.updateForm.value.endTime.split(':')[0]
          }],
        rating: 4
        // creditStore: 0
  
      };
    console.log("dbscjdbskvjbkfj"+mentor2);

    // this.service.sucess();
    this.service.saveMentor(mentor2).subscribe(data =>{
      this.snackbar
      // tslint:disable-next-line: max-line-length
      .open(("Your Profile is Updated"), '', {
        verticalPosition: 'top',
        duration:1000
       })
      this.mentor = data
      console.log(mentor2);
    })

  }
  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 600) ? 1 : 2;
  }
  Logout(){
    Cookie.deleteAll()
    sessionStorage.clear();
  }
}
