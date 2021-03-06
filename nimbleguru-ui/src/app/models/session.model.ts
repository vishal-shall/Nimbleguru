import { timeslot } from './time.model';

export interface Session
{
    sessionId : String;
    subject: String;
    duration: String;
    message:String;
    sessionVideoUrl:String;
    startTime:String;
    endTime:String;
    studentEmail:String;
    teacherEmail:String;
    sessionChatMessage:Array<String>;
    query:String;
    sessionStatus?:String
}
