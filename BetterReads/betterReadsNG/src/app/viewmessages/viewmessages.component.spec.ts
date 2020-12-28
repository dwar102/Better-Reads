import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewmessagesComponent } from './viewmessages.component';

describe('ViewmessagesComponent', () => {
  let component: ViewmessagesComponent;
  let fixture: ComponentFixture<ViewmessagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewmessagesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewmessagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
