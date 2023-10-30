import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TankStatesComponent } from './tank-states.component';

describe('TankStatesComponent', () => {
  let component: TankStatesComponent;
  let fixture: ComponentFixture<TankStatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TankStatesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TankStatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
