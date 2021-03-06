import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChatApplicationComponent } from './chat-application/chat-application.component';
import { ChatHistoryComponent } from './chat-history/chat-history.component';
import { FaqBySubjectComponent } from './faq-by-subject/faq-by-subject.component';
//import { ChatHistoryComponent } from './chat-history/chat-history.component';
import { FaqComponent } from './faq/faq.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LogInPageComponent } from './log-in-page/log-in-page.component';
import { MentorProfileComponent } from './mentor-profile/mentor-profile.component';
import { MentorRegistrationComponent } from './mentor-registration/mentor-registration.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterationPageComponent } from './registeration-page/registeration-page.component';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { StudentliveconnectComponent } from './studentliveconnect/studentliveconnect.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import {AuthGuardService as AuthGuard} from './auth-guard.service';
const routes: Routes = [
  { path: "student-profile", component: StudentProfileComponent,canActivate: [AuthGuard] },
  { path: "login", component: LogInPageComponent },
  { path: "student-registration", component: RegisterationPageComponent },
  { path: "home", component: HomepageComponent,canActivate: [AuthGuard] },
  { path: "mentor-profile", component: MentorProfileComponent,canActivate: [AuthGuard] },
  { path: 'feedback/:sid', component: FeedbackComponent,canActivate: [AuthGuard] },
  { path: "update-profile", component: UpdateProfileComponent,canActivate: [AuthGuard] },
  { path: "frequently-asked-questions", component: FaqComponent,canActivate: [AuthGuard] },
  { path: "chat/:id", component: ChatApplicationComponent,canActivate: [AuthGuard] },
  { path: "mentor-registration", component: MentorRegistrationComponent,canActivate: [AuthGuard] },
  {path: "faq/:subject", component: FaqBySubjectComponent,canActivate: [AuthGuard]},
  { path: "history/:sessionId", component: ChatHistoryComponent,canActivate: [AuthGuard] },
  { path: '', component: LandingPageComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
