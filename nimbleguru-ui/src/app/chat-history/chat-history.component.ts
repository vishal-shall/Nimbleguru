import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Session } from 'inspector';
import { data } from 'jquery';
import { Cookie } from 'ng2-cookies';
import { MentorProfileService } from '../mentor-profile.service';
import { MessageModel } from '../models/message.model';
import { RegisterModel } from '../models/register.model';
import { SessionService } from '../session.service';
import { StudentProfileService } from '../student-profile.service';

@Component({
  selector: 'app-chat-history',
  templateUrl: './chat-history.component.html',
  styleUrls: ['./chat-history.component.css']
})

export class ChatHistoryComponent implements OnInit {
  message1: MessageModel = new MessageModel();
  email1: String = this.service.abc();
  name = this.service.abc().split('@')[0].toUpperCase();

  // user: RegisterModel;
  sessionId:any;
  avatar=this.name.charAt(0).toUpperCase();


  messageHistory:MessageModel[];
  constructor(private _route:ActivatedRoute ,private service: StudentProfileService,private sessionService: SessionService, private router:Router ) {
    this._route.params.subscribe(params => {
      console.log("params" , params["sessionId"])
      this.sessionId = params["sessionId"];
    })
  
  
  
  }

  ngOnInit(): void {
    // this.service.getByEmail(this.email1).subscribe(data => this.user = data);
    // console.log(this.user.sessions[5]);
    this.sessionService.getMessageDetails(this.sessionId).subscribe(data=>
      this.messageHistory = data["sessionChatMessages"]);
      console.log(data)
  }

  Logout(){
    Cookie.deleteAll()
    sessionStorage.clear();
  }

  onProfile(){
    if(this.service.getRole()=='M'){
      this.router.navigate(['/mentor-profile'])
    }
    else{
      this.router.navigate(['/student-profile'])
    }
  }

 }
