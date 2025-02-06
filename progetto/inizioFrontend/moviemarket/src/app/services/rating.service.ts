import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Definiamo l'interfaccia Rating direttamente qui
export interface Rating {
    userId: string;
    movieId: string;
    rating: number;
}

@Injectable({
    providedIn: 'root'
})
export class RatingService {
    // URL base per le API REST del rating (modifica se necessario)
    private baseUrl = 'http://localhost:8080/ratings';

    constructor(private http: HttpClient) {}

    // Aggiunge un nuovo rating
    addRating(rating: Rating): Observable<void> {
        return this.http.post<void>(`${this.baseUrl}`, rating);
    }

    // Aggiorna un rating esistente
    updateRating(rating: Rating): Observable<void> {
        return this.http.put<void>(`${this.baseUrl}/user/${rating.userId}/movie/${rating.movieId}`, rating);
    }

    // Elimina il rating di un utente per un film
    deleteRating(userId: string, movieId: string): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/user/${userId}/movie/${movieId}`);
    }

    // Recupera il rating di un utente per un film
    getUserRating(userId: string, movieId: string | null): Observable<Rating> {
        return this.http.get<Rating>(`${this.baseUrl}/user/${userId}/movie/${movieId}`);
    }

    // Recupera tutte le valutazioni per un film
    getRatingsByMovie(movieId: string): Observable<Rating[]> {
        return this.http.get<Rating[]>(`${this.baseUrl}/movie/${movieId}`);
    }

    // Recupera il rating medio per un film
    getAverageRating(movieId: string | null): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}/average/movie/${movieId}`);
    }
}
