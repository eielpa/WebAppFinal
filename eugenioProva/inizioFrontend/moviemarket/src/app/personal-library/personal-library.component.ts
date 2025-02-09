import { Component, OnInit } from '@angular/core';
import { PersonalLibraryService, Movie } from '../services/personal-library.service';
import { DownloadService} from '../services/download.service';
import { NgIf, NgForOf } from '@angular/common';

@Component({
  selector: 'app-personal-library',
  templateUrl: './personal-library.component.html',

  standalone: true,
  imports: [NgIf, NgForOf],
  styleUrls: ['./personal-library.component.css']
})
export class PersonalLibraryComponent implements OnInit {
  userNickname: string | null = null;
  movies: Movie[] = [];
  errorMessage: string = '';

  constructor(
      private personalLibraryService: PersonalLibraryService,
      private downloadService: DownloadService
  ) {}

  ngOnInit(): void {
    // Recupera il nickname dell'utente dal sessionStorage
    this.userNickname = sessionStorage.getItem('userNickname');

    if (this.userNickname) {
      this.getMovies();
    } else {
      this.errorMessage = 'Utente non autenticato o non presente in sessione.';
    }
  }

  // Metodo per richiedere i film dal backend
  getMovies(): void {
    this.personalLibraryService.getMoviesByUser(this.userNickname!).subscribe({
      next: (data: Movie[]) => {
        console.log('Film ricevuti:', data);
        this.movies = data;
      },
      error: (error: any) => {
        console.error('Errore nel recupero dei film:', error);
        this.errorMessage = 'Si è verificato un errore nel recupero dei film.';
      }
    });
  }

  // Metodo per gestire il download del film
  downloadMovie(movie: Movie): void {
    if (this.userNickname) {
      // Converte movie.id in stringa se necessario
      this.downloadService.downloadMovie(this.userNickname, movie.title);
    } else {
      console.error('Utente non autenticato');
    }
  }

}