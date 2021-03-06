import { BrowserModule } from '@angular/platform-browser';
import { InjectionToken, NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { RegisterationPageComponent } from './registeration-page/registeration-page.component';
import { LogInPageComponent } from './log-in-page/log-in-page.component';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatCard, MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatFormField, MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { MentorProfileComponent } from './mentor-profile/mentor-profile.component';
import {MatSelectModule} from '@angular/material/select';
import { HomepageComponent } from './homepage/homepage.component';
import { StudentliveconnectComponent } from './studentliveconnect/studentliveconnect.component';
import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering';
import { StudentProfileService } from './student-profile.service';
import {MatSlider, MatSliderModule} from '@angular/material/slider';
import {MatSnackBarModule} from '@angular/material/snack-bar';

import { DatePipe } from '@angular/common'
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import {AuthenticationService} from './services/authentication.service';
// import {BasicAuthHtppInterceptorService} from './services/BasicAuthHtppInterceptorService';
import { FeedbackComponent } from './feedback/feedback.component';
import { FeedbackService } from './feedback.service';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { MatDialogModule } from '@angular/material/dialog';

import { ChatApplicationComponent } from './chat-application/chat-application.component';
import { FaqComponent } from './faq/faq.component';
import { MentorProfileService } from './mentor-profile.service';
import { JwtClientService } from './jwt-client.service';
import { MentorRegistrationComponent } from './mentor-registration/mentor-registration.component';
import { ChatHistoryComponent } from './chat-history/chat-history.component';
//import { InputsModule } from '@progress/kendo-angular-inputs';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {PagerService} from './PagerService';

import { MatMenuModule} from '@angular/material/menu';
import { FaqBySubjectComponent } from './faq-by-subject/faq-by-subject.component';
import { AuthService } from './auth.service';
import { AuthGuardService } from './auth-guard.service';
import { JwtHelperService, JWT_OPTIONS, JwtModule } from '@auth0/angular-jwt';






@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    RegisterationPageComponent,
    LogInPageComponent,
    StudentProfileComponent,
    PageNotFoundComponent,
    MentorProfileComponent,
    HomepageComponent,
    StudentliveconnectComponent,
    FeedbackComponent,
    UpdateProfileComponent,
    ChatApplicationComponent,
    FaqComponent,
    MentorRegistrationComponent,
    ChatHistoryComponent,
    FaqBySubjectComponent,
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatProgressBarModule,
    MatGridListModule,
    MatSelectModule,
    MatCardModule,
    MatListModule,
    MatToolbarModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    HttpClientModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule,
    MatSnackBarModule,
    FormsModule,
    MatDialogModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatMenuModule,
    JwtModule


  ],
  // providers: [ PagerService,JwtClientService ,StudentProfileService, MentorProfileService ,FeedbackService,DatePipe,AuthenticationService,BasicAuthHtppInterceptorService,{ provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }],
  providers: [{ provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,AuthService,AuthGuardService, PagerService,JwtClientService ,StudentProfileService, MentorProfileService ,FeedbackService,DatePipe,AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
