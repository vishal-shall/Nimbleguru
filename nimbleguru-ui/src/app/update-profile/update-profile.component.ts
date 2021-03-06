import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MentorProfileService } from '../mentor-profile.service';
import { Router } from '@angular/router';
import { Mentor } from '../models/mentor.model';
import { MatDialogRef } from '@angular/material/dialog';
import { MentorProfileComponent } from '../mentor-profile/mentor-profile.component';
import { StudentProfileService } from '../student-profile.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  mentor: Mentor = new Mentor();
  updateForm: FormGroup;
  hide = true;
  breakpoint: number;
  subjects: any[] = [ 'Physics', 'Maths', 'English', 'Java', 'Chemistry','Civics', 'Economics' , 'Python' , 'Biology','History' , 'Accounts' ,'Business Administration' , 'Geography', 'Political Science'];
  time11: any[] = ['00:00', '1:00', '2:00', '3:00', '4:00', '5:00', '6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'];
  constructor(private formBuilder: FormBuilder, private service1: StudentProfileService, private service: MentorProfileService, private router: Router, private dialogRef: MatDialogRef<MentorProfileComponent>,private  snackbar:MatSnackBar) { }
  email1: String = this.service1.abc();

  ngOnInit(): void {
    this.service.getByEmail(this.email1).subscribe(data => this.mentor = data);
    this.updateForm = this.formBuilder.group({
      name: [this.mentor.name, [
        Validators.required
      ]],
      email: [this.email1, [
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
      startTime: [this.mentor.time, [Validators.required]],
      endTime: [this.mentor.time, [Validators.required,]]
    });
    this.breakpoint = (window.innerWidth <= 600) ? 1 : 2;
  }
  OnUpdate() {
    const mentor1: Mentor = {
      id: this.updateForm.value.id,
      name: this.updateForm.value.name,
      phoneNumber: this.updateForm.value.phoneNumber,
      email: this.updateForm.value.email,
      qualification: this.updateForm.value.qualification,
      subject: this.updateForm.value.subject,
      status:this.updateForm.value.status,
      joining_date:this.updateForm.value.joining_date,
      comment:this.updateForm.value.comment,
      sessions: this.updateForm.value.sessions,
      time:[
      {
        "startTime": this.updateForm.value.startTime.split(':')[0],
        "endTime": this.updateForm.value.endTime.split(':')[0]
      }],
      rating: this.updateForm.value.rating,
      creditStore: this.updateForm.value.creditStore

    };
    console.log(this.updateForm.value);
    this.service.updateMentor(mentor1).subscribe(data => {
      this.mentor = data});
      this.snackbar
      // tslint:disable-next-line: max-line-length
      .open(("Your Profile is Updated"), '', {
        verticalPosition: 'top',
        duration:2000
       })
       this.dialogRef.close();

  }


  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 600) ? 1 : 2;
  }
  Onclose(){
    this.dialogRef.close();
  }
}
