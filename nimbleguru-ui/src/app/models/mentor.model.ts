import { Session } from './session.model';
import { timeslot } from './time.model';
export class Mentor
{
    // tslint:disable-next-line: ban-types
    id?: number;
    name: String;
    phoneNumber: String;
    email: String;
    qualification: String;
    subject: String[];
    time: timeslot[];
    sessions: Session[];
    creditStore? : any;
    rating : any;
    status:String;
    joining_date:String;
    comment:String;
}
