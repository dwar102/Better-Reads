import { TestBed } from '@angular/core/testing';

import { FriendshipServiceService } from './friendship-service.service';

describe('FriendshipServiceService', () => {
  let service: FriendshipServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FriendshipServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
