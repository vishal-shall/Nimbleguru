import { Session } from './session.model';


export class RegisterModel{
    name: String;
    phoneNumber: number;
    email: any;
    standard: number;
    password: String
    confirmPassword: String;
    sessions: Session[];
    time : any
}