import { Component, Input, OnInit, ViewChild , ChangeDetectorRef, OnDestroy } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { StudentProfileService } from '../student-profile.service';
import { StudentProfileComponent } from '../student-profile/student-profile.component';
import { UpdateProfileComponent } from '../update-profile/update-profile.component';
import {MentorProfileService} from '../mentor-profile.service';
import {Mentor} from '../models/mentor.model';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MessagingService } from '../services/messaging.service';
import { Message } from '@stomp/stompjs';
// import { AuthenticationService } from '../services/authentication.service';
import { StompState } from '@stomp/ng2-stompjs';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../services/authentication.service';
import { Cookie } from 'ng2-cookies';
import { SessionService } from '../session.service';
import { PagerService } from '../PagerService';
import { Session } from 'protractor';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {MatPaginator} from '@angular/material/paginator';
import { MatTableDataSource} from '@angular/material/table';
import { Observable } from 'rxjs';

const WEBSOCKET_URL = 'ws://localhost:8082/socket';
// const WEBSOCKET_URL = 'ws:/localhost:8080/mentor-profile-service/socket';
const EXAMPLE_URL = '/topic/server-broadcaster';

@Component({
  selector: 'app-mentor-profile',
  templateUrl: './mentor-profile.component.html',
  styleUrls: ['./mentor-profile.component.css']
})
export class MentorProfileComponent implements OnInit,OnDestroy


{
  sessionArray: Session[];
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  obs: Observable<any>;
  dataSource: MatTableDataSource<Session>;
  private messagingService: MessagingService;
  name = this.service.abc().split('@')[0].toUpperCase();

  messageHistory: String;
  state: String = 'NOT CONNECTED';
  subject: String= 'Maths';
  public mentor: Mentor;
  doubt: String;
  isShow = true;
  sessionId: any;
  session:any;
  avatar=this.name.charAt(0).toUpperCase();
  // CurrentSessionId = this.MentorProfileService.getSessionId();
  constructor(private changeDetectorRef: ChangeDetectorRef,private http: HttpClient,private pagerService: PagerService,private snackbar:MatSnackBar, private MentorProfileService: MentorProfileService , private service: StudentProfileService,private dialog:MatDialog,private authenticate: AuthenticationService,private router:Router,private ss:SessionService ) {
    this.messagingService = new MessagingService(WEBSOCKET_URL, EXAMPLE_URL);
    
    this.MentorProfileService.getSessionByEmail(this.email).subscribe(x => {this.sessionArray = x;
    // dataSource: MatTableDataSource<Session> = new MatTableDataSource<Session>(this.sessionArray);
    this.dataSource = new MatTableDataSource<Session>(this.sessionArray);
    console.log(this.sessionArray);
    console.log(this.dataSource);
    this.changeDetectorRef.detectChanges();
    this.dataSource.paginator = this.paginator;
    this.obs = this.dataSource.connect();
  });


    // Subscribe to its stream (to listen on messages)
    this.messagingService.stream().subscribe((message: Message) => {
      this.messageHistory = message.body;
      this.isShow = true;
      this.ss.getMentorEmail(this.service.abc(),"Active").subscribe(data=>{
        this.session=data;
        console.log("hello"+this.session.sessionId);
        this.snackbar
        // tslint:disable-next-line: max-line-length
        .open((message.body + ''), 'Connect', {
          verticalPosition: 'top',
          // duration:1000
         })
         .onAction()
         .subscribe(() =>
         this.router.navigate([`/chat/${this.session.sessionId}`]))
         ;
         console.log("vkvkvkvk"+this.MentorProfileService.getSessionId())
      console.log(message);
    });

      })



    // Subscribe to its state (to know its connected or not)
    this.messagingService.state().subscribe((state: StompState) => {
      this.state = StompState[state];
    });
  }
  email: String = this.service.abc();
    // email:String ="khan3578@gmail.com"
    private allItems: any;
    pager: any = {};
    pagedItems: any[];

  ngOnInit()
  {
    // this.http.get(`http://localhost:8086/session/${this.email}`).subscribe(data => {
    //     // set items to json response
    //     this.allItems = data;
    //     console.log(this.allItems);

    //     // initialize to page 1
    //     this.setPage(1);
    // });

  //   this.http.get(`http://localhost:8089/sessions/${this.email}`).subscribe(x => {this.sessionArray = x;
  //   // dataSource: MatTableDataSource<Session> = new MatTableDataSource<Session>(this.sessionArray);
  //   this.dataSource = new MatTableDataSource<Session>(this.sessionArray);
  //   console.log(this.sessionArray);
  //   console.log(this.dataSource);
  // });
      console.log(this.email);
      this.MentorProfileService.getByEmail(this.email).subscribe(data => this.mentor = data);
      Cookie.set('sessionId', this.MentorProfileService.getSessionId());
    //  console.log(this.mentor1);
    // this.messagingService.getSubject(this.subject).subscribe(data => this.)
  }
  ngOnChanges(){
    this.isShow=false;
  }

  OnCreate()
  {
  const dialogConfig = new MatDialogConfig();
 //  this.MentorProfileService.populatedForm(row);
  dialogConfig.disableClose = true;
  dialogConfig.autoFocus = true;
  dialogConfig.width = "60%";
  this.dialog.open(UpdateProfileComponent,dialogConfig);
  }

  // sendAction() {
  //   console.log('Sending message');
  //   this.messagingService.send('/server-receiver', {
  //     text: 'This is cool',
  //     text2: 'I\'m so happy!'
  //   });
  // }
  Logout(){
    Cookie.deleteAll()
    sessionStorage.clear();
  }
//   setPage(page: number) {
//     if (page < 1 || page > this.pager.totalPages) {
//         return;
//     }

//     // get pager object from service
//     this.pager = this.pagerService.getPager(this.allItems.length, page);

//     // get current page of items
//     this.pagedItems = this.allItems.slice(this.pager.startIndex, this.pager.endIndex + 1);
// }
ngOnDestroy() {
  if (this.dataSource) { 
    this.dataSource.disconnect(); 
  }
}

}

