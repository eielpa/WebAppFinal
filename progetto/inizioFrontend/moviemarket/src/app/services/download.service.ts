import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class DownloadService {

    // URL per il file statico (servito da Spring Boot)
    private downloadUrl = 'http://localhost:8080/download';

    constructor(private http: HttpClient) { }

    async downloadMovie(userId: string, movieId: string): Promise<void> {
        try {
            // Componi l'URL con i parametri di query
            const urlWithParams = `${this.downloadUrl}?userId=${encodeURIComponent(userId)}&movieId=${encodeURIComponent(movieId)}`;
            // Utilizziamo lastValueFrom per ottenere il blob dalla chiamata HTTP
            const blob = await lastValueFrom(
                this.http.get(urlWithParams, { responseType: 'blob' })
            );
            // Crea un URL temporaneo per il blob
            const url = window.URL.createObjectURL(blob);
            // Crea un elemento <a> per simulare il click sul link
            const a = document.createElement('a');
            a.href = url;
            // Imposta il nome del file da scaricare
            a.download = 'provaFilm.txt';
            // Aggiunge l'elemento al DOM, simula il click e poi lo rimuove
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            // Rilascia l'URL temporaneo
            window.URL.revokeObjectURL(url);
        } catch (error) {
            console.error('Errore durante il download del file', error);
        }
    }
}