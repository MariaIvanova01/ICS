import { TestBed } from '@angular/core/testing';

import { SingleImageService } from './single-image.service';

describe('SingleImageService', () => {
  let service: SingleImageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SingleImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
