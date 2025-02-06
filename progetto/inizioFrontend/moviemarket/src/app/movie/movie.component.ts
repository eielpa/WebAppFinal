import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule, NgForOf } from '@angular/common';
import { MovieService } from '../services/movie.service';
import { MovieCardComponent } from '../moviecard/moviecard.component';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-movie',
  imports: [CommonModule, NgForOf, MovieCardComponent],
  templateUrl: './movie.component.html',
  standalone: true,
  styleUrls: ['./movie.component.css']  // Assicurati che il nome sia "styleUrls" (con la "s")
})
export class MoviesComponent implements OnInit {
  categories: any[] = []; // Lista di generi
  // Ora ogni oggetto include anche l'id, necessario per la navigazione
  moviesByGenre: { id: number, genre: string, movies: any[] }[] = [];

  constructor(
      private categoryService: CategoryService,
      private movieService: MovieService,
      private router: Router
  ) {}

  ngOnInit(): void {
    // Ottieni tutti i generi
    this.categoryService.getAllCategories().subscribe((categories) => {
      // Limita a 15 generi
      this.categories = categories.slice(0, 15);

      // Per ogni genere, carica i film corrispondenti
      this.categories.forEach((category) => {
        this.movieService.getMoviesByCategory(category.id).subscribe((movies) => {
          // Aggiungi anche l'id del genere per poter navigare alla pagina corretta
          this.moviesByGenre.push({ id: category.id, genre: category.name, movies: movies });
        });
      });
    });
  }

  scroll(genre: string, direction: string) {
    const movieList = document.querySelector(`.movies-row[data-genre="${genre}"]`);
    if (movieList) {
      const scrollAmount = 300; // Puoi modificare la distanza di scorrimento
      movieList.scrollBy({ left: direction === 'left' ? -scrollAmount : scrollAmount, behavior: 'smooth' });
    }
  }

  // Metodo per la navigazione verso la pagina del genere
  goToGenre(genreData: { id: number, genre: string, movies: any[] }): void {
    // Naviga all'URL, ad esempio "/genre/ID", dove GenreComponent potrà usare l'id per caricare i dati
    this.router.navigate(['/genre', genreData.id]);
  }
}
