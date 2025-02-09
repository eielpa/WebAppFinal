import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieCardTopRatedComponent } from './moviecard-toprated.component';

describe('MovieCardTopRatedComponent', () => {
  let component: MovieCardTopRatedComponent;
  let fixture: ComponentFixture<MovieCardTopRatedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovieCardTopRatedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieCardTopRatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
