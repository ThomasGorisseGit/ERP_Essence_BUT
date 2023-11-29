import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectClientComponent } from './connect-client.component';

describe('ConnectClientComponent', () => {
  let component: ConnectClientComponent;
  let fixture: ComponentFixture<ConnectClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConnectClientComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConnectClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
