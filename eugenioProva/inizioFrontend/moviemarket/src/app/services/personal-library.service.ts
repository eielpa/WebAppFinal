import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';

export interface Movie {
    id: number;
    title: string;
    downloadUrl: string;
}

export interface PersonalLibrary {
    id: number;
    userNickname: string;
    movies: Movie[];
}

@Injectable({
    providedIn: 'root'
})
export class PersonalLibraryService {
    // Assicurati che il path sia coerente con quello esposto dal backend.
    private apiUrl = 'http://localhost:8080/personal-library';

    constructor(private http: HttpClient) {}

    getMoviesByUser(userNickname: string): Observable<Movie[]> {
        return this.http.get<Movie[]>("http://localhost:8080/personal-library/user/" + userNickname).pipe(
            map((movies: Movie[]) => {
                console.log('Film della libreria personale:', movies);
                return movies;
            })
        );
    }

}