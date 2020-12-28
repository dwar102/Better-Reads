import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewmediaComponent } from './viewmedia.component';

describe('ViewmediaComponent', () => {
  let component: ViewmediaComponent;
  let fixture: ComponentFixture<ViewmediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewmediaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewmediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
