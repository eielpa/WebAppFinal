  import { Routes } from '@angular/router';
  import { HomeComponent} from './home/home.component';
  import { GenreComponent} from './genre/genre.component';
  import { MoviesComponent } from "./movie/movie.component";
  import { PersonalLibraryComponent } from './personal-library/personal-library.component';
  import { MovieSpecificComponent} from "./movie-specific/movie-specific.component";
  import { RecuperoPasswordComponent} from "./passwordrecovery/passwordrecovery.component";
  import { AuthComponent } from "./auth/auth.component";
  import { WishlistComponent } from "./wishlist/wishlist.component";
  import { AggiungiFilmComponent } from "./aggiungi-film/aggiungi-film.component";
  import {PaymentSuccessComponent} from "./payment-success/payment-success.component";

  export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'genre/:name', component: GenreComponent },
    { path: 'films', component: MoviesComponent },
    { path: 'movie/:title', component: MovieSpecificComponent },
    { path: 'recupero-password', component: RecuperoPasswordComponent },
    { path: 'auth', component: AuthComponent },
    { path: 'personal-library', component: PersonalLibraryComponent },
    { path: 'wishlist', component: WishlistComponent },
    { path: 'add-movie', component: AggiungiFilmComponent },
    { path: 'payment-success', component: PaymentSuccessComponent },
  ];
