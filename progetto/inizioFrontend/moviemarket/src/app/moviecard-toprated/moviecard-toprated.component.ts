import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-moviecard-toprated',
  templateUrl: './moviecard-toprated.component.html',
  standalone: true,
  styleUrls: ['./moviecard-toprated.component.css']
})
export class MovieCardTopRatedComponent {
  @Input() movie: any;
  @Input() movieIndex: number = 1;

  constructor(private router: Router) {}

  goToMovieDetails() {
    this.router.navigate(['/movie', this.movie.title], {
      state: { description: this.movie.description }
    });
  }
}
