import { Component, OnInit } from '@angular/core';
import { RegisterModel } from '../models/register.model';
import { FormBuilder, FormControl, FormGroup, PatternValidator, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StudentProfileService } from '../student-profile.service';
import { MatSnackBar, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { Cookie } from 'ng2-cookies';


@Component({
  selector: 'app-registeration-page',
  templateUrl: './registeration-page.component.html',
  styleUrls: ['./registeration-page.component.css']
})
export class RegisterationPageComponent implements OnInit {
  user: RegisterModel = new RegisterModel();
  registerForm: FormGroup;
  hide = true;
  breakpoint: number;
  password1: any;
  userName:any;
  constructor(private formBuilder: FormBuilder, private router: Router, private service: StudentProfileService , private snackbar: MatSnackBar) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: [this.user.name, [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]+ [a-zA-Z]+$/)
      ]],
      phoneNumber: [this.user.phoneNumber, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(10),
        Validators.pattern('^[0-9]*$')
      ]],
      email: [this.user.email, [
        Validators.required,
        Validators.email
      ]],
      standard: [this.user.standard, [
        Validators.required,
        Validators.pattern('^[0-9]*$'),
        Validators.minLength(1),
        Validators.maxLength(2)
      ]],
      password: [this.user.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30),
      ]],
      confirmPassword: [this.user.confirmPassword, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30),
        ]]
    });
    this.breakpoint = (window.innerWidth <= 600) ? 1 : 2;
    // this.breakpoint = (window.innerWidth <= 100) ? 2 : 3;


  }
  // tslint:disable-next-line: typedef
  onRegisterSubmit() {
    if (this.check() == true){
      this.service.registerFromRemote(this.user).subscribe(
        data => {
          // console.log('response recived');
          this.router.navigate(['/login']);
        }, err =>{
          this.snackbar.open(('User with this emailID already exists. Kindly register with different emailID.'), '', {
            duration: 5000,
            verticalPosition: 'top'
           });
          //  console.clear();

          // console.log("bad credentials");
        }
      );
    }
    else{
      this.snackbar.open(('Your passwords aren\'t matching! Please check your password fields'), '', {
        duration: 10000,
        verticalPosition: 'top'
       });

    }
    // console.clear();


  }
  gotologin() {
    this.router.navigate(['/login']);
  }
  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 600) ? 1 : 2;
  }
  check(){
    if (this.user.confirmPassword == this.user.password){
      return true;
    }
  }
}


