import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentsmediaComponent } from './commentsmedia.component';

describe('CommentsmediaComponent', () => {
  let component: CommentsmediaComponent;
  let fixture: ComponentFixture<CommentsmediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentsmediaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentsmediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
