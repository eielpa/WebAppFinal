import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MovieCardComponent } from '../moviecard/moviecard.component';
import { MovieService } from '../services/movie.service';
import { TMDbService } from '../services/tmdb.service';
import { RatingService, Rating } from '../services/rating.service';

@Component({
  selector: 'app-movie-specific',
  templateUrl: './movie-specific.component.html',
  styleUrls: ['./movie-specific.component.css'],
  standalone: true,
  imports: [CommonModule, MovieCardComponent]
})
export class MovieSpecificComponent implements OnInit {
  movie: any = null;
  movieTitle: string | null = '';
  movieDescription: string = '';
  movieImage: string = ''; // URL dell'immagine
  categoryMovieId: number | null | undefined;
  relatedMovies: any[] = [];
  userId: string | null = null;
  errorMessage: string = '';

  // Variabili per il rating
  globalRating: number = 0;    // Media dei rating del film
  personalRating: number = 0;  // Rating personale dell'utente

  constructor(
      private route: ActivatedRoute,
      private movieService: MovieService,
      private tmdbService: TMDbService,
      private router: Router,
      private ratingService: RatingService
  ) {}

  ngOnInit() {
    // Recupera il titolo dal parametro della route
    this.route.paramMap.subscribe(params => {
      this.movieTitle = params.get('title');
      this.userId = sessionStorage.getItem('userNickname');

      if (this.movieTitle) {
        this.loadMovieDetails(this.movieTitle);

        // Imposta immagine e descrizione in base allo state o richiama il servizio
        if (history.state && history.state.imageUrl) {
          this.movieImage = history.state.imageUrl;
        } else {
          this.tmdbService.getMovieImage(this.movieTitle).subscribe(imageUrl => {
            this.movieImage = imageUrl;
          });
        }

        if (history.state && history.state.description) {
          this.movieDescription = history.state.description;
        } else {
          this.movieDescription = 'Descrizione non disponibile';
        }

        // Carica i dettagli del film per ottenere il categoryId e ulteriori informazioni
        this.movieService.getMoviesByTitle(this.movieTitle).subscribe(movie => {
          if (movie) {
            if (!(history.state && history.state.description)) {
              this.movieDescription = movie.description || 'Descrizione non disponibile';
            }
            this.categoryMovieId = movie.categoryId;
            if (this.categoryMovieId != null) {
              this.getRelatedMovies(this.categoryMovieId);
            }
            // Carica il rating globale e, se presente, quello personale
            this.loadGlobalRating(this.movieTitle);
            if (this.userId) {
              this.loadPersonalRating(this.userId, this.movieTitle);
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
    if (!this.movieTitle) return;

    if(!this.userId){
      alert("Non sei loggato, verrai reindirizzato alla schermata di login");
      this.router.navigate(['/auth']);
      return;
    }

    this.movieService.addToWishlist(this.userId, this.movieTitle).subscribe({
      next: () => alert(`${this.movieTitle} è stato aggiunto alla tua wishlist!`),
      error: () => console.error('Errore nell\'aggiunta alla wishlist')
    });
  }

  private loadMovieDetails(title: string) {
    this.movieService.getMoviesByTitle(title).subscribe({
      next: data => { this.movie = data; },
      error: () => this.errorMessage = 'Errore nel caricamento del film'
    });
  }

  pay(movieTitle: string | null) {
    this.movieService.checkout(movieTitle, 0);
  }

  // Metodi per gestire il rating

  loadGlobalRating(movieId: string | null) {
    this.ratingService.getAverageRating(movieId).subscribe({
      next: (avg: number) => { this.globalRating = avg; },
      error: (err: any) => console.error('Errore nel caricare il rating globale', err)
    });
  }

  loadPersonalRating(userId: string, movieId: string | null) {
    this.ratingService.getUserRating(userId, movieId).subscribe({
      next: (rating: Rating) => { this.personalRating = rating.rating; },
      error: (err: any) => {
        console.log('Nessun rating personale trovato', err);
        this.personalRating = 0;
      }
    });
  }

  // Metodo invocato al click su una stella per il rating personale
  onPersonalRatingChange(newRating: number) {
    this.personalRating = newRating;
    const ratingData: Rating = {
      userId: this.userId!,
      movieId: this.movieTitle!,
      rating: newRating
    };

    // Proviamo a recuperare il rating personale: se esiste, lo aggiorniamo, altrimenti lo salviamo
    this.ratingService.getUserRating(this.userId!, this.movieTitle!).subscribe({
      next: () => {
        // Se esiste, aggiorna il rating
        this.ratingService.updateRating(ratingData).subscribe({
          next: () => {
            console.log('Rating aggiornato');
            this.loadGlobalRating(this.movieTitle!);
          },
          error: (err: any) => console.error('Errore nell\'aggiornare il rating', err)
        });
      },
      error: () => {
        // Se non esiste, aggiungi il rating
        this.ratingService.addRating(ratingData).subscribe({
          next: () => {
            console.log('Rating aggiunto');
            this.loadGlobalRating(this.movieTitle!);
          },
          error: (err: any) => console.error('Errore nell\'aggiungere il rating', err)
        });
      }
    });
  }
}
