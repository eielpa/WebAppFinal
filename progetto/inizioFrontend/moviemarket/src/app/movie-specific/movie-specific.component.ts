import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from "../services/movie.service";
import { CommonModule } from "@angular/common";
import { MovieCardComponent } from "../moviecard/moviecard.component";
import { TMDbService } from "../services/tmdb.service";
import {LoginService} from "../services/login.service";

@Component({
  selector: 'app-movie-specific',
  templateUrl: './movie-specific.component.html',
  standalone: true,
  styleUrls: ['./movie-specific.component.css'],
  imports: [CommonModule, MovieCardComponent]
})
export class MovieSpecificComponent implements OnInit {
  movieTitle: string | null = '';
  movieDescription: string = '';
  movieImage: string = ''; // URL dell'immagine
  categoryMovieId: number | null | undefined;
  relatedMovies: any[] = [];
  userId: string | null = null;

  constructor(
      private route: ActivatedRoute,
      private movieService: MovieService,
      private tmdbService: TMDbService,
      private router: Router,
      private loginService: LoginService // aggiunto per il controllo login
  ) {}

  ngOnInit() {
    // Recupera il titolo dal parametro della route
    this.route.paramMap.subscribe(params => {
      this.movieTitle = params.get('title');
      this.userId = sessionStorage.getItem('userNickname');

      if (this.movieTitle) {
        // Se è presente l'immagine nello state, usala direttamente
        if (history.state && history.state.imageUrl) {
          this.movieImage = history.state.imageUrl;
        } else {
          // Altrimenti, richiama il servizio per ottenerla
          this.tmdbService.getMovieImage(this.movieTitle).subscribe(imageUrl => {
            this.movieImage = imageUrl;
          });
        }



        // Se è presente la descrizione nello state, usala direttamente
        if (history.state && history.state.description) {
          this.movieDescription = history.state.description;
        } else {
          this.movieDescription = 'Descrizione non disponibile';
        }

        // Chiamata sempre al servizio per recuperare i dati del film (per ottenere almeno il categoryId)
        this.movieService.getMoviesByTitle(this.movieTitle).subscribe(movie => {
          if (movie) {
            // Se non era presente la descrizione nello state, la aggiorniamo con quella recuperata
            if (!(history.state && history.state.description)) {
              this.movieDescription = movie.description || 'Descrizione non disponibile';
            }
            this.categoryMovieId = movie.categoryId;
            if (this.categoryMovieId !== null && this.categoryMovieId !== undefined) {
              this.getRelatedMovies(this.categoryMovieId);
            }
          }
        });
      }
    });
  }



  getRelatedMovies(categoryId: number) {
    this.movieService.getMoviesByCategory(categoryId).subscribe(movies => {
      // Escludi il film corrente dalla lista dei film correlati
      this.relatedMovies = movies.filter(m => m.title !== this.movieTitle);
    });
  }

  scroll(list: HTMLElement, direction: string) {
    const scrollAmount = 300;
    list.scrollBy({ left: direction === 'left' ? -scrollAmount : scrollAmount, behavior: 'smooth' });
  }

  addToWishlist() {
    if (!this.movieTitle || !this.userId) return;

    this.movieService.addToWishlist(this.userId, this.movieTitle).subscribe({
      next: () => console.log('Film aggiunto alla wishlist'),
      error: () => console.error('Errore nell\'aggiunta alla wishlist')
    });
  }
}
