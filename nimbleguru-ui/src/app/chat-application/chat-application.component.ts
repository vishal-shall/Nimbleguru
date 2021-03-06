import { Component, OnInit } from '@angular/core';
import { MessageModel } from '../models/message.model';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';

import { StudentProfileService } from '../student-profile.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MentorProfileService } from '../mentor-profile.service';
import { Mentor } from '../models/mentor.model';
import { Session } from '../models/session.model';
import { SessionService } from '../session.service';
import { Cookie } from 'ng2-cookies';
import { MatMenuModule} from '@angular/material/menu';
import { A11yModule } from '@angular/cdk/a11y';


@Component({
  selector: 'app-chat-application',
  templateUrl: './chat-application.component.html',
  styleUrls: ['./chat-application.component.css']
})
export class ChatApplicationComponent {
  CurrentsessionId = this.ms.getSessionId();
  mentor: Mentor;
  session:any;
  receivedMessage: MessageModel = new MessageModel();
  senderEmail: String = this.service.abc().split('@')[0];
  //  email2:String=this.ms.getMentorEmail();
  private serverUrl = 'http://localhost:8089/socket'
  // private serverUrl = 'http://localhost:8080/mentor-platfrom-service/socket'
  private title = 'WebSockets chat';
  private stompClient;
  sid:String;

  name = this.service.abc().split('@')[0].toUpperCase();
  avatar=this.name.charAt(0).toUpperCase();


  constructor(private service: StudentProfileService, private router: Router, private ms: MentorProfileService, private ss: SessionService, private _route: ActivatedRoute) {
    this.initializeWebSocketConnection();
    // this.email1=this.service.abc();
  }

  initializeWebSocketConnection() {
    this._route.params.subscribe(params => {
      console.log("params" , params["id"])
      this.sid = params["id"];
    })
    // this.ss.getMentorEmail(this.service.abc(),"Active").subscribe(data=>{
    //   this.session=data;
    //   this.sid=this.session.sessionId;
    //   console.log(this.session.sessionId);
    // });
    console.log("dcnkjcnkvnfdk"+this.CurrentsessionId);
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe(`/chat/${that.sid}`, (message) => {
        that.receivedMessage = JSON.parse(message.body);

        if (message) {
          $(".chat").append("<div style='background-color:wite;color:black;padding:5px;font-size:14px; font-weight:600; text-transform:capitalize' class='message'>" + that.receivedMessage.userName + " " + "<span style='background-color:white;color:grey;padding:5px;font-size:14px' >" + that.receivedMessage.timeStamp + "</span>" + "</div>")
            .append("<div style='background-color:white;color:black;padding:5px;font-size:18px; padding-left:5px' class='message'> " + that.receivedMessage.messageContent + "</div>")
        }
      });
    });
  }

  sendMessage(msg) {
    this.receivedMessage.userName = this.senderEmail;
    //  this.message1.receiver=this.email2;
    this.receivedMessage.messageContent = msg;
    const send = {
      sender: this.senderEmail,
      // receiver:this.email2,
      content: msg
    };
    console.log('hello ' + JSON.stringify(this.receivedMessage));
    //console.log("ddd"+this.session.sessionId);
    this.stompClient.send(`/app/send/message/${this.sid}`, {}, JSON.stringify(this.receivedMessage));
    $('#input').val('')
  }
  endChat() {

  }

  onClick=(e)=>{
    console.log(e);
    console.log(this.sid);
    // this.ss.endSession(this.sid).subscribe(data=>{
    //   this.session=data;
    //   console.log(data);
    // })
    if (this.service.getRole() == 'M') {
      this.ss.endSession(this.sid).subscribe(data=>{
        this.session=data;
        console.log(data);
      })
      this.router.navigate(['/frequently-asked-questions']);
    }
    else {
      this.router.navigate([`feedback/${this.sid}`]);
    }


}
Logout(){
  Cookie.deleteAll()
  sessionStorage.clear();
}
}

