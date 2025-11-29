import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MisCitas } from './mis-citas';

describe('MisCitas', () => {
  let component: MisCitas;
  let fixture: ComponentFixture<MisCitas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MisCitas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MisCitas);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
