import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieSpecificComponent } from './movie-specific.component';

describe('MovieSpecificComponent', () => {
  let component: MovieSpecificComponent;
  let fixture: ComponentFixture<MovieSpecificComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovieSpecificComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieSpecificComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
