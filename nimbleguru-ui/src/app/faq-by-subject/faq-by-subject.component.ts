import { Component, Input, OnInit } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { FAQ } from '../models/faq.model';
import { StudentProfileService } from '../student-profile.service';

@Component({
  selector: 'app-faq-by-subject',
  templateUrl: './faq-by-subject.component.html',
  styleUrls: ['./faq-by-subject.component.css']
})
export class FaqBySubjectComponent implements OnInit {
 subject: String;
//  queries:String[];
 faq: FAQ[];
  constructor(private studentProfileService: StudentProfileService) { }
  name = this.studentProfileService.abc().split('@')[0].toUpperCase();
  avatar=this.name.charAt(0).toUpperCase();

  ngOnInit(): void {
    // this.studentProfileService.getFaqBySubject(this.subject).subscribe(data=> this.subject=data);
    // console.log(this.subject)
    this.subject = this.studentProfileService.getSubject();
    console.log(this.studentProfileService.getSubject());
    this.studentProfileService.getFaqBySubject(this.subject).subscribe(data => {this.faq = data;
      console.log(this.faq)});
  }
Logout() {
    Cookie.deleteAll()
    sessionStorage.clear();
  }
}
