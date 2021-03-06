import { Component, OnInit } from '@angular/core';
import { LoginModel } from '../models/login.model';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms'
import { Router, RouterLink } from '@angular/router';
import { StudentProfileService } from '../student-profile.service';
import { JwtClientService } from '../jwt-client.service';
import { Mentor } from '../models/mentor.model';
import { MentorProfileService } from '../mentor-profile.service';
import { AuthenticationService } from '../services/authentication.service';
import { MentorProfileComponent } from '../mentor-profile/mentor-profile.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MentorRegistrationComponent } from '../mentor-registration/mentor-registration.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.css']
})
export class LogInPageComponent implements OnInit {
  user: LoginModel = new LoginModel();
  loginForm: FormGroup;
  hide = true;
  breakpoint: number;
  email: String = this.service.abc();
  response: any;
  authRequest: any;
  token: any;
  mentor: Mentor;
  invalidLogin = false;
  constructor( private snackbar: MatSnackBar,private dialog:MatDialog,private formBuilder: FormBuilder, private router: Router, private authentication: JwtClientService, private service: StudentProfileService, private authenticate: AuthenticationService, private Mservice: MentorProfileService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      'email': [this.user.email, [
        Validators.required,
        Validators.email
      ]],
      'password': [this.user.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]]
    });
    this.breakpoint = (window.innerWidth <= 600) ? 1 : 2;
  }

  Login() {
    this.authRequest = {
      'email': this.user.email,
      'password': this.user.password
    }
    // this.getAccessToken(this.authRequest);
    this.authenticate.authenticate(this.user.email, this.user.password).subscribe(
      data => {
        this.token = data.token
        let roleService = this.token[1];
        this.service.setRole(roleService);
        let userEmail = this.token[0];
        console.log(this.token);
        this.service.emailService(userEmail);
        console.log(this.token.size+"sfad"+this.token.length)
        if(this.token.length == 3){
        if (roleService == 'M') {
          this.Mservice.getByEmail(userEmail).subscribe(data => {
            this.mentor = data

            console.log(this.mentor)
            if (this.mentor == null) {
              this.router.navigate(['mentor-registration'])
            }
            else {
              this.router.navigate(['/mentor-profile']);
            }
          });


        }
        else {
          this.router.navigate(['/home'])
        }
      }
      else{
        this.snackbar.open(('Username or password Incorrect'), 'Login again', {
          duration: 3000,
          verticalPosition: 'top',
         })

      }


      },
      // error => {
      // //  this.invalidLogin = true

      // }
    );

  }


  gotoregisteration() {
    this.router.navigate(["/student-registration"])
  }
  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 600) ? 1 : 2;
  }


  // public getAccessToken(authRequest) {
  //   let resp = this.authentication.generateToken(authRequest);
  //   resp.subscribe((data: any) => {
  //     this.token = data.token

  //     let roleService = this.token[1];
  //     let userEmail = this.token[0];
  //     this.service.emailService(userEmail);
  //     this.service.setRole(roleService);
  //     console.log(this.service.abc())
  //     console.log(roleService)
  //     if (roleService == 'M') {
  //       this.Mservice.getByEmail(userEmail).subscribe(data => {
  //         this.mentor = data
  //         console.log(this.mentor)
  //         if (this.mentor == null) {
  //           this.router.navigate(['mentor-registration'])
  //         }
  //         else {
  //           this.router.navigate(['/mentor-profile']);
  //         }
  //       });


  //     }
  //     else {
  //       this.router.navigate(['/home'])
  //     }


  //   })

  // }


}
