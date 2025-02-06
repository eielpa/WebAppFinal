import {Component, Input} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../../services/movie.service'
import { WishlistService } from "../../services/wishlist.service";
import { WishlistComponent } from "../../wishlist/wishlist.component";
import {state} from "@angular/animations";
import {OnInit} from "@angular/core";

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html',
  standalone: true,
  imports: [WishlistComponent]
})
export class PaymentSuccessComponent {
  movie: string = '';
  userNickname: string = '';
  movieId: number = 0;
  idProva: number = 0;



    constructor(private router: Router, private route: ActivatedRoute, private movieService: MovieService, private wishlistService: WishlistService) {
        this.route.queryParams.subscribe(params => {
            this.movie = params['movie'];
            this.userNickname = params['user'];

            // Converte 'idProva' in un numero, se presente
            const idProvaString = params['idProva'];
            this.idProva = idProvaString ? Number(idProvaString) : 0; // Se idProva è presente, lo converte in numero, altrimenti assegna 0

            this.addMovieToLibrary(this.idProva);
        });
    }



    private addMovieToLibrary(movieId: number): boolean {
    this.movieService.addMovieToLibrary(this.userNickname, this.movie).subscribe({
      next: response => {
        console.log('Film aggiunto alla libreria:', response);
      },
      error: err => {
        console.error('Errore nell\'aggiunta del film:', err);
      }
    });

    this.removeFromWishlist(movieId);

    return true;
  }

  private removeFromWishlist(id: number) {
      this.wishlistService.removeFromWishlist(id).subscribe({
        next: () => {
          console.log("Film rimosso correttamente dalla wishlist.");
          // Dopo che il film è stato rimosso, aggiungi alla libreria

        },
        error: err => {
          console.error('Errore nella rimozione del film:', err);
        }
      });

  }

  goToLibrary() {
    this.router.navigate(["/personal-library"]); // Reindirizza alla libreria
  }
}