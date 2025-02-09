import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {
    private selectedCategoryId = new BehaviorSubject<number | null>(null);
    selectedCategoryId$ = this.selectedCategoryId.asObservable(); // Observable per gli altri componenti
    constructor(private http: HttpClient) {}

    // Recupera tutte le categorie
    getAllCategories(): Observable<any[]> {
        return this.http.get<any[]>("http://localhost:8080/categories/allCategories");
    }

    getCategoryNameById(id: number): Observable<string> {
        return this.http.get<string>("http://localhost:8080/categories/getNameById/" + id, { responseType: 'text' as 'json' });
    }


    setSelectedCategory(id: number) {
        this.selectedCategoryId.next(id); // Imposta il valore dell'ID selezionato
    }
}
