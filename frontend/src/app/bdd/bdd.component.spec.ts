import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BddComponent } from './bdd.component';

describe('BddComponent', () => {
  let component: BddComponent;
  let fixture: ComponentFixture<BddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
