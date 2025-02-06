🎬**MovieMarket**
Web app che permette di esplorare un vasto catalogo di film, scaricarli e gestire i pagamenti in modo sicuro tramite Stripe. Le immagini dei film vengono caricate automaticamente grazie all'integrazione con TMDb.

🚀 Funzionalità
Esplora e scarica film 🎥
Pagamenti sicuri con Stripe 💳
Caricamento automatico delle immagini da TMDb 🖼️
Wishlist per salvare i film che vuoi guardare 📌
Possibilità di recensire un film ⭐

🛠️ Tecnologie utilizzate
Frontend: Angular standalone
Backend: Spring Boot con Postgres
Autenticazione: SessionStorage per la gestione dell'utente
Integrazioni:
  -Stripe per i pagamenti
  -TMDb per le immagini dei film

Accedendo con email: admin@gmail.com password: admin, si sblocca la possibilità di gestire i film presenti, per rimuoverli o aggiungerli
La carta per testare Stripe in modalità sandbox è "4242 4242 4242 4242" utilizzando una qualunque data di scadenza e un qualunque cvv.
Per simulare il download, non disponendo dei file mp4 dei film, è stato inserito un file di testo di prova, all'interno della cartella resources/static
Per accedere al database usare user "postgres" con la propria password ed effettuare il dump del database "movies"
Prima di runnare angular, eliminare la cartella node modules e fare npm install, a causa di problemi con le dipendenze di vite

  
Progetto WebApplication di Eugenio Ielpa, Giuseppe Niccolò Perfetti, Jago Francesco Pulicanò, Salvatore Perrelli
