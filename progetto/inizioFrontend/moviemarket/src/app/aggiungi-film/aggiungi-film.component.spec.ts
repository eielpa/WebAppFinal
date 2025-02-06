import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AggiungiFilmComponent } from './aggiungi-film.component';

describe('AggiungiFilmComponent', () => {
  let component: AggiungiFilmComponent;
  let fixture: ComponentFixture<AggiungiFilmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AggiungiFilmComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AggiungiFilmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
