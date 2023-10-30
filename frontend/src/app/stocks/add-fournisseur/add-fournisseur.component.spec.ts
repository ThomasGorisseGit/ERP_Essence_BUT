import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFournisseurComponent } from './add-fournisseur.component';
import { MatSelectModule } from '@angular/material/select';

describe('AddFournisseurComponent', () => {
  let component: AddFournisseurComponent;
  let fixture: ComponentFixture<AddFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({

      declarations: [AddFournisseurComponent, MatSelectModule]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AddFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
