import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface WishlistItem {
    id: number;
    userId: string;
    movieId: string;
}

@Injectable({
    providedIn: 'root'
})
export class WishlistService {
    private baseUrl = 'http://localhost:8080/wishlist';
    removeId: number = 0;

    constructor(private http: HttpClient) { }

    setRemoveId(id: number) {
        this.removeId = id;
    }

    removeFromWishlist(id: number | null | undefined): Observable<string> {
        return this.http.delete<string>(`${this.baseUrl}/remove/${id}`);
    }

    getUserWishlist(): Observable<WishlistItem[]> {
        const userId = sessionStorage.getItem('userNickname');
        if (!userId) {
            throw new Error('Utente non autenticato.');
        }
        return this.http.get<WishlistItem[]>("http://localhost:8080/wishlist/user/" + userId);
    }

}