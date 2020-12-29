import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WritemessageComponent } from './writemessage.component';

describe('WritemessageComponent', () => {
  let component: WritemessageComponent;
  let fixture: ComponentFixture<WritemessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WritemessageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WritemessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
