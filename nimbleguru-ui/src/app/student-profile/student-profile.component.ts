import { CompileShallowModuleMetadata } from '@angular/compiler';
import { Component, OnInit,OnDestroy, ViewChild, ChangeDetectorRef } from '@angular/core';
import { LogInPageComponent } from '../log-in-page/log-in-page.component';
import { LoginModel } from '../models/login.model';
import { RegisterModel } from '../models/register.model';
import { StudentProfileService } from '../student-profile.service';
import {Mentor} from '../models/mentor.model';
import { SessionService } from '../session.service';
import {Session} from '../models/session.model';
import {MatPaginator} from '@angular/material/paginator';
import { MatTableDataSource} from '@angular/material/table';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';


export interface Card {
  title: string;
  subtitle: string;
  text: string
}

const DATA: Card[] = [
  {
    title: 'Shiba Inu 1',
    subtitle: 'Dog Breed',
    text: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan.'
  },
  {
    title: 'Shiba Inu 2',
    subtitle: 'Dog Breed',
    text: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan.'
  }
];
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css'] 
})

export class StudentProfileComponent implements OnInit ,OnDestroy{
  name = this.studentProfileService.abc().split('@')[0].toUpperCase();

  sessionArray: Session[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  obs: Observable<any>;
  dataSource: MatTableDataSource<Session>;
  user: RegisterModel;
  avatar=this.name.charAt(0).toUpperCase();


  breakpoint:number;
  constructor(private http: HttpClient,private changeDetectorRef: ChangeDetectorRef,private studentProfileService:StudentProfileService,private authenticate: AuthenticationService,private SessionService:SessionService) {
    this.studentProfileService.getSessionByEmail(this.email).subscribe(x => {this.sessionArray = x;
    // dataSource: MatTableDataSource<Session> = new MatTableDataSource<Session>(this.sessionArray);
    
    this.dataSource = new MatTableDataSource<Session>(this.sessionArray);
    console.log(this.sessionArray);
    console.log(this.dataSource);
    this.changeDetectorRef.detectChanges();
    this.dataSource.paginator = this.paginator;
    this.obs = this.dataSource.connect();
  });
    
  // dataSource2: MatTableDataSource<Session> = new MatTableDataSource<Session>(this.sessionArray);
    // this.SessionService.getSessionByEmail(this.email).subscribe((res: Response) => {
    //   this.sessionArray = res.json();
    // console.log(this.sessionArray);
    // this.dataSource = new MatTableDataSource<Session>(this.sessionArray);
    // console.log(this.sessionArray); });
  
    // this.dataSource = new MatTableDataSource<Session>(this.sessionArray);
}
  email:String = this.studentProfileService.abc();
  session:String = "Geometry";
  date:String = "31/12/2020 12:00 AM";
  


  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 600) ? 1 : 2;

    // this.sessionStr = {sessionId:1,sessionDate:"31/12/2020",studentName:"Ayush",mentorName:"simanta",query:"Algebra funxtions",querySolutin:"link for materials",message:"refer the link",rating:4,startTime:3,endTime:5};
    this.studentProfileService.getByEmail(this.email).subscribe(data => this.user = data);
    console.log(this.sessionArray);
    



    // this.changeDetectorRef.detectChanges();
    // this.dataSource.paginator = this.paginator;
    // this.obs = this.dataSource.connect();

    // this.studentProfileService.getBySubject(this.subject).subscribe(data=> this.mentor123 = data);
    // console.log(this.subject);
  }
  ngOnDestroy() {
    if (this.dataSource) { 
      this.dataSource.disconnect(); 
    }
  }
  
  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 600) ? 1 : 2;
  }

  // getHistory(){
  //   this.
  // }
  Logout(){
    Cookie.deleteAll()
    sessionStorage.clear();
  }
}
