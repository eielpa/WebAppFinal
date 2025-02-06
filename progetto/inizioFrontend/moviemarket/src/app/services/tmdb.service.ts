import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class TMDbService {
    private apiKey = 'cece316032af6247efa753de96645ac5'; // 🔴 Sostituisci con la tua API Key
    private apiUrl = 'https://api.themoviedb.org/3';

    constructor(private http: HttpClient) {}

    // 🔹 Ottenere l'immagine di un film per titolo
    getMovieImage(title: string): Observable<string> {
        return this.http.get<any>(`${this.apiUrl}/search/movie?api_key=${this.apiKey}&query=${title}&language=it-IT`)
            .pipe(
                map(response => {
                    const movie = response.results[0]; // Prende il primo risultato
                    return movie ? `https://image.tmdb.org/t/p/w500${movie.poster_path}` : '';
                })
            );
    }


}
