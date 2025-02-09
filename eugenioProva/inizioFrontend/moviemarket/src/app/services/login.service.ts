import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface UserInfo {
    id: string;
    email: string;
    isAdmin: boolean;
}

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    private apiUrl = 'http://localhost:8080/auth'; // URL del backend

    constructor(private http: HttpClient) {}

    login(email: string, password: string): Observable<{ sessionId: string, isAdmin: boolean , nickname: string}> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
        const body = new URLSearchParams();
        body.set('email', email);
        body.set('password', password);

        return this.http.post<{ sessionId: string, isAdmin: boolean , nickname: string}>(
            "http://localhost:8080/auth/login",
            body.toString(),
            { headers }
        ).pipe(
            catchError((error) => {
                return throwError(() => new Error(error.message || "Errore di connessione"));
            })
        );
    }

    getUserNickname(): Observable<string> {
        const sessionId = sessionStorage.getItem('sessionId');
        if (!sessionId) return throwError(() => new Error("No active session"));

        return this.http.get(`http://localhost:8080/auth/userInfo?sessionId=${sessionId}`, { responseType: 'text' }).pipe(
            catchError((error) => {
                return throwError(() => new Error(error.message || "Errore nel recupero del nickname"));
            })
        );
    }

    getUserInfo(): Observable<UserInfo> {
        const sessionId = sessionStorage.getItem('sessionId');
        if (!sessionId) return throwError(() => new Error("No active session"));

        return this.http.get<UserInfo>(`http://localhost:8080/auth/userInfo?sessionId=${sessionId}`).pipe(
            catchError((error) => {
                return throwError(() => new Error(error.message || "Errore nel recupero delle informazioni utente"));
            })
        );
    }

    getUserByEmail(email: string): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/getUserByEmail?email=${email}`);
    }

    // Metodo per aggiornare la password
    updatePassword(email: string, newPassword: string): Observable<any> {
        return this.http.post(`${this.apiUrl}/updatePassword`, { email, newPassword }, { responseType: 'text' });
    }

}
