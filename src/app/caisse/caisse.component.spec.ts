import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaisseComponent } from './caisse.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { PumpStatesComponent } from '../pump-states/pump-states.component';
import { TankStatesComponent } from '../tank-states/tank-states.component';
import { IncidentsComponent } from '../incidents/incidents.component';
import { FormsModule } from '@angular/forms';

describe('CaisseComponent', () => {
  let component: CaisseComponent;
  let fixture: ComponentFixture<CaisseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, FormsModule],
      declarations: [CaisseComponent, PumpStatesComponent, TankStatesComponent, IncidentsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CaisseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
