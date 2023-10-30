import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReapproComponent } from './add-reappro.component';

describe('AddReapproComponent', () => {
  let component: AddReapproComponent;
  let fixture: ComponentFixture<AddReapproComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddReapproComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddReapproComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
