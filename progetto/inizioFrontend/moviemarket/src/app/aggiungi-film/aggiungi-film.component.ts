import { Component, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { CategoryService } from '../services/category.service';
import { Router } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";

interface Category {
  id: number;
  name: string;
}

@Component({
  selector: 'app-aggiungi-film',
  templateUrl: './aggiungi-film.component.html',
  standalone: true,
  imports: [ FormsModule, CommonModule ],
  styleUrls: ['./aggiungi-film.component.css']
})
export class AggiungiFilmComponent implements OnInit {
  movie = {
    title: '',
    description: '',
    releaseYear: null,
    categoryId: null,
    rating: null
  };

  movieToDelete: { id: number | null; name: string } = { id: null, name: '' };
  categories: Category[] = [];
  isAddMode: boolean = true; // Modalità iniziale è "aggiungi"

  constructor(
      private movieService: MovieService,
      private categoryService: CategoryService,
      private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe({
      next: (categories) => {
        this.categories = categories;
      },
      error: (err) => {
        console.error('Errore nel caricamento delle categorie', err);
      }
    });
  }

  // Funzione per inviare il form di aggiunta film
  submitForm(): void {
    if (this.isFormValid()) {
      this.movieService.addMovie(this.movie).subscribe({
        next: () => {
          alert('Film aggiunto con successo!');
          this.router.navigate(['/films']);
        },
        error: (err) => {
          alert('Errore nell\'aggiungere il film');
          console.error(err);
        }
      });
    } else {
      alert('Per favore, compila tutti i campi correttamente.');
    }
  }

  // Funzione di validazione del form
  isFormValid(): boolean {
    return (
        this.movie.title?.trim() !== '' &&
        this.movie.description?.trim() !== '' &&
        this.movie.releaseYear !== null &&
        this.movie.categoryId !== null
    );
  }

  // Funzione per rimuovere un film
  removeMovie(): void {
    if (this.movieToDelete.id !== null && this.movieToDelete.name.trim() !== '') {
      this.movieService.getMovieById(this.movieToDelete.id).subscribe({
        next: (movie) => {
          if (movie.title === this.movieToDelete.name) {
            this.movieService.deleteMovie(this.movieToDelete.id).subscribe({
              next: () => {
                alert('Film rimosso con successo!');
              },
              error: (err) => {
                alert('Errore nella rimozione del film');
                console.error(err);
              }
            });
          } else {
            alert('Errore: il titolo non corrisponde all\'ID fornito.');
          }
        },
        error: () => {
          alert('Errore: nessun film trovato con questo ID.');
        }
      });
    } else {
      alert('Inserisci un ID e un nome validi.');
    }
  }

  // Metodo per cambiare modalità
  toggleMode(): void {
    this.isAddMode = !this.isAddMode; // Cambia la modalità tra "aggiungi" e "rimuovi"
  }
}
