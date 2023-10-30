import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PumpStatesComponent } from './pump-states.component';

describe('PumpStatesComponent', () => {
  let component: PumpStatesComponent;
  let fixture: ComponentFixture<PumpStatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PumpStatesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PumpStatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
