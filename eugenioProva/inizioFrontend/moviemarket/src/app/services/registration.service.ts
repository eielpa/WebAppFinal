import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

const headers = new HttpHeaders({
    'Content-Type': 'application/json'
});

export interface User {
    id: string;
    firstName: string;
    lastName: string;
    email: string;
    dob: string; // oppure Date, a seconda del formato gestito dal backend
    password: string;
}


@Injectable({
    providedIn: 'root'
})
export class RegistrationService {

    constructor(private http: HttpClient) { }


    // Metodo per registrare l'utente
    registerUser(user: User): Observable<any> {
        user.dob = new Date(user.dob).toISOString().split('T')[0];
        return this.http.post("http://localhost:8080/auth/register", user, {
            headers,
            responseType: 'text'
        }).pipe(catchError(this.handleError)); // Gestisce gli errori
    }



    // Funzione per gestire gli errori delle richieste HTTP
    private handleError(error: HttpErrorResponse) {
        let errorMessage = 'Unknown error!';
        if (error.error instanceof ErrorEvent) {
            // Errore del client (es. rete)
            errorMessage = `Client-side error: ${error.error.message}`;
        } else {
            // Errore del server
            errorMessage = `Server-side error: ${error.status} - ${error.message}`;
        }
        return throwError(() => new Error(errorMessage));
    }
}
