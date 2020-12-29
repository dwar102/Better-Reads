import { TestBed } from '@angular/core/testing';

import { UsertagService } from './usertag.service';

describe('UsertagService', () => {
  let service: UsertagService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsertagService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
