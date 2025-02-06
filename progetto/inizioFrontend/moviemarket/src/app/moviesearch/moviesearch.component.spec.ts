import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoviesearchComponent } from './moviesearch.component';

describe('MoviesearchComponent', () => {
  let component: MoviesearchComponent;
  let fixture: ComponentFixture<MoviesearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MoviesearchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MoviesearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
