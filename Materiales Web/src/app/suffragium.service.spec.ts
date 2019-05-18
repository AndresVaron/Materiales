import { TestBed } from '@angular/core/testing';

import { SuffragiumService } from './suffragium.service';

describe('SuffragiumService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SuffragiumService = TestBed.get(SuffragiumService);
    expect(service).toBeTruthy();
  });
});
