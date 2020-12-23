import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddmediaComponent } from './addmedia.component';

describe('AddmediaComponent', () => {
  let component: AddmediaComponent;
  let fixture: ComponentFixture<AddmediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddmediaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddmediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
