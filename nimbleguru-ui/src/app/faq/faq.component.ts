import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { MentorProfileService } from '../mentor-profile.service';
import { FAQ } from '../models/faq.model';
import { StudentProfileService } from '../student-profile.service';
import { StudentProfileComponent } from '../student-profile/student-profile.component';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {
  name = this.service1.abc().split('@')[0].toUpperCase();

  faq : FAQ = new FAQ();
  updateForm1: FormGroup
  hide = true;
  breakpoint: number;
  avatar=this.name.charAt(0).toUpperCase();

  constructor(private formBuilder: FormBuilder,private service1:StudentProfileService,private service:MentorProfileService , private router:Router ) {}

  ngOnInit(): void 
  {
    this.updateForm1 = this.formBuilder.group({
      subject: [this.faq.subject, [
        Validators.required
      ]],
      solution: [this.faq.solution, [
        Validators.required,
      ]],
      query: [this.faq.query, [
        Validators.required,
      ]],
    });
    this.breakpoint = (window.innerWidth <= 600) ? 1 : 2;
  }
  OnSave()
  {
    console.log(this.updateForm1.value);
    const faq1 : FAQ = {
         subject : this.updateForm1.value.subject,
         query : this.updateForm1.value.query,
         solution : this.updateForm1.value.solution
       };
    this.service.saveFaq(faq1).subscribe(data =>{
      this.faq = data
      console.log(this.faq);
    })
    this.router.navigate(['/mentor-profile']);  }

    Logout(){
      Cookie.deleteAll()
      sessionStorage.clear();
    }
}
