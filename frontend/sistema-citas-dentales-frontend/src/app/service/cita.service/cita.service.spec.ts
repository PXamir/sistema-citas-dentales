/* tslint:disable:no-unused-variable */

import { TestBed } from '@angular/core/testing';
import { CitasService } from './cita.service';

describe('Service: Cita', () => {
  let service: CitasService;
  
  beforeEach(() => {
      TestBed.configureTestingModule({});
      service = TestBed.inject(CitasService);
    });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
