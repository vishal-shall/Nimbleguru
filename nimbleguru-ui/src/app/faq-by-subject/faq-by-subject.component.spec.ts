import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaqBySubjectComponent } from './faq-by-subject.component';

describe('FaqBySubjectComponent', () => {
  let component: FaqBySubjectComponent;
  let fixture: ComponentFixture<FaqBySubjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FaqBySubjectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FaqBySubjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
