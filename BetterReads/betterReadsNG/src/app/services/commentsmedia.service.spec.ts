import { TestBed } from '@angular/core/testing';

import { CommentsmediaService } from './commentsmedia.service';

describe('CommentsmediaService', () => {
  let service: CommentsmediaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommentsmediaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
