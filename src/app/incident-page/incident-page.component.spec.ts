import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncidentPageComponent } from './incident-page.component';
import { IncidentsComponent } from '../incidents/incidents.component';

describe('IncidentPageComponent', () => {
  let component: IncidentPageComponent;
  let fixture: ComponentFixture<IncidentPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [IncidentPageComponent, IncidentsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(IncidentPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
