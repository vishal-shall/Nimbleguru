import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentliveconnectComponent } from './studentliveconnect.component';

describe('StudentliveconnectComponent', () => {
  let component: StudentliveconnectComponent;
  let fixture: ComponentFixture<StudentliveconnectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentliveconnectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentliveconnectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
