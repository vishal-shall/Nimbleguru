import { Component, OnInit } from '@angular/core';

interface Problem{
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-studentliveconnect',
  templateUrl: './studentliveconnect.component.html',
  styleUrls: ['./studentliveconnect.component.css']
})
export class StudentliveconnectComponent implements OnInit {
  problems: Problem[] = [
    {value: '0', viewValue: 'Video issue'},
    {value: '1', viewValue: 'Connection issue'},
    {value: '2', viewValue: 'Mentor issue'}
  ];
  teachername="MentorName";
  noofdoubts="NoofDoubtsSolved";

  constructor() { }

  ngOnInit(): void {
  }

}
