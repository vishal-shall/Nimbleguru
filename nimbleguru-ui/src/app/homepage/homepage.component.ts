import { Route } from '@angular/compiler/src/core';
import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { MatSelectChange } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterModule } from '@angular/router';
import { MentorProfileService } from '../mentor-profile.service';
import { Mentor } from '../models/mentor.model';
import { AuthenticationService } from '../services/authentication.service';
import { StudentProfileService } from '../student-profile.service';
import { Session } from '../models/session.model';
import { SessionService } from '../session.service';
import { MatMenuTrigger } from '@angular/material/menu';
import { MatDialog } from '@angular/material/dialog';
import { Cookie } from 'ng2-cookies';
import {MatMenuModule} from '@angular/material/menu';
import { HttpClient } from '@angular/common/http';



interface Problem {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  // name= "vishal.kapoor@svn".split('@')[0]
  constructor(private studentProfileService: StudentProfileService, private snackbar: MatSnackBar, private router: Router, private authenticate: AuthenticationService
    ,         private mentorProfileService: MentorProfileService, private ss: SessionService, public dialog: MatDialog) { }
  @ViewChild('menuTrigger') menuTrigger: MatMenuTrigger;

  value = '';
  str: String;
  name = this.studentProfileService.abc().split('@')[0].toUpperCase();
  // tslint:disable-next-line: ban-types
  subject: String;
  session: Session;
  sessionId1: String;
  avatar=this.name.charAt(0).toUpperCase();
  // tslint:disable-next-line: variable-name
  mentor_subject: Mentor;
  problems: String[] = [ 'Physics', 'Maths', 'English', 'Java', 'Chemistry','Civics', 'Economics' , 'Python' , 'Biology','History' , 'Accounts' ,'Business Administration' , 'Geography', 'Political Science'];
  update(value: string) { this.value = value; }
  //   {value: '0', viewValue: 'Physics'},
  //   {value: '1', viewValue: 'Chemistry'},
  //   {value: '2', viewValue: 'Maths'},
  //   {value: '3', viewValue: 'English'},
  //   {value: '4', viewValue: 'Hindi'},
  //   {value: '5', viewValue: 'Computer Science'},
  //   {value: '6', viewValue: 'Social Studies'},

  // ];
  ngOnInit(): void {
    // this.studentProfileService.setSubject(this.subject);

  }
  // tslint:disable-next-line: typedef


  getBySubject() {
    this.studentProfileService.getBySubject(this.subject).subscribe(data => {
      this.mentor_subject = data,
        console.log('hello' + data.email);
      // this.mentorProfileService.setMentorEmail(data.email);
      //  this.session.mentorName=data.email;
      //   this.session.studentName=this.studentProfileService.abc();
      const genertaeSession = {
        studentEmail: this.studentProfileService.abc(),
        teacherEmail: data.email,
      };
      console.log(genertaeSession);
      this.ss.findSession(genertaeSession).subscribe(res => {
          this.session = res;
          this.session.subject = this.subject;
          // this.session.query = document.getElementById("queryDoubt").nodeValue;
          this.session.query = this.str;
          console.log('asdsad' + this.str);
          // this.http.post<any>('http://localhost:8089/query',this.session);
          this.mentorProfileService.setQuerySubject(this.session).subscribe(data => console.log(data));
          console.log(this.session.query +'daasad'+ this.session.subject);
          console.log(this.subject +'this is the data'+ this.session.sessionId);
          const sess = this.session.sessionId;
          this.sessionId1 = this.session.sessionId;
          this.mentorProfileService.setSessionId(sess);
          console.log('dhbvcjv'+ this.mentorProfileService.getSessionId());

      });
      this.snackbar
        // tslint:disable-next-line: max-line-length
        .open((data.name + ' with degree in ' + data.qualification + ' has been assigned to you. Now you can proceed with your doubt'), 'Connect', {

          verticalPosition: 'top'
        })
        .onAction()
        .subscribe(() =>
        this.router.navigate([`/chat/${this.mentorProfileService.getSessionId()}`]))
        //  console.log("dhbvcjv"+this.mentorProfileService.getSessionId());

        // .subscribe(() => this.router.navigate([`/chat`]))
        ;
      //  console.log(data.name.toString())
    },
    err => {
      this.snackbar.open(('We are not able find mentor right now. Please Wait for sometime. By that time checkout our FAQ section'), 'Go To FAQs', {
        duration: 10000,
        verticalPosition: 'top',
       })
       .onAction()
        .subscribe(() => {this.studentProfileService.getFaqBySubject(this.subject),
          console.log(this.subject),
          this.router.navigate([`/faq/${this.subject}`]);
      }); }
      // this.mentor_subject = data
    );
    // this.selectedsessionId=this.session.sessionId;
    // console.log(this.session.sessionId);
    // console.log(this.subject);
  }

  selectedSubject(event: MatSelectChange) {
    console.log(event.value);
    this.subject = event.value;
    this.studentProfileService.setSubject(event.value);
  }

  // getNotification(){
  //  this.mentorProfileService
  // }
  Logout() {
    Cookie.deleteAll();
    sessionStorage.clear();
  }

}
