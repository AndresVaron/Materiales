import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MapaDepartamentosComponent } from './mapa-departamentos.component';

describe('MapaDepartamentosComponent', () => {
  let component: MapaDepartamentosComponent;
  let fixture: ComponentFixture<MapaDepartamentosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MapaDepartamentosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapaDepartamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
