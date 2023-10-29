import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingPageComponent } from './landing-page.component';
import { PumpStatesComponent } from '../pump-states/pump-states.component';
import { TankStatesComponent } from '../tank-states/tank-states.component';
import { IncidentsComponent } from '../incidents/incidents.component';

describe('LandingPageComponent', () => {
  let component: LandingPageComponent;
  let fixture: ComponentFixture<LandingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LandingPageComponent, PumpStatesComponent, TankStatesComponent, IncidentsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(LandingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
