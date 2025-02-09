import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { CategoryService } from '../services/category.service';
import { LoginService } from '../services/login.service';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  standalone: true,
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  categories: any[] = [];
  isDropdownOpen = false; // Stato per il controllo dell'apertura del dropdown
  isMenuOpen = false; // Controlla l'apertura del menu su dispositivi mobili
  nickname: string | null = '';
  isAdmin: boolean = false;
  userNickname: string | null = '';

  @Output() searchEvent = new EventEmitter<string>();

  constructor(
      private categoryService: CategoryService,
      private loginService: LoginService,
      private router: Router,
      private movieService: MovieService
  ) {}

  ngOnInit(): void {
    // Carica le categorie per il menu a tendina
    this.categoryService.getAllCategories().subscribe((categories) => {
      this.categories = categories;
    });

    // Recupera le informazioni utente se loggato
    if (this.isLoggedIn()) {
      this.loginService.getUserNickname().subscribe({
        next: (nickname) => this.userNickname = nickname,
        error: () => this.userNickname = null
      });

      // Recupera lo stato admin
      this.loginService.getUserInfo().subscribe({
        next: (userInfo) => {
          this.nickname = userInfo.id;
          sessionStorage.setItem('userNickname', this.nickname);
          this.isAdmin = userInfo.isAdmin;
        },
        error: () => this.isAdmin = false
      });
    }
  }

  onSearch(event: Event) {

    const searchText = (event.target as HTMLInputElement).value;
    this.router.navigate(['']);
    this.searchEvent.emit(searchText);
  }

  // Alterna la visibilità del dropdown
  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  // Alterna la visibilità del menu mobile
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  @HostListener('document:click', ['$event'])
  clickOut(event: MouseEvent) {
    const clickedInside = (event.target as HTMLElement).closest('.navbar');
    if (!clickedInside) {
      this.isDropdownOpen = false;
      this.isMenuOpen = false; // Chiude anche il menu mobile se si clicca fuori
    }
  }

  // Naviga alla pagina del genere selezionato e chiude il menu
  onCategoryChange(categoryId: number): void {
    this.categoryService.setSelectedCategory(categoryId);
    this.router.navigate(['/genre', categoryId]);
    this.isDropdownOpen = false;
    this.isMenuOpen = false;
  }

  // Verifica se l'utente è loggato (presenza di un token/sessionId)
  isLoggedIn(): boolean {
    return !!sessionStorage.getItem('sessionId');
  }

  // Vai alla pagina della Wishlist
  goToWishlist(): void {
    this.router.navigate(['/wishlist']);
  }

// Vai alla pagina della Libreria
  goToLibrary(): void {
    this.router.navigate(['/personal-library']);
  }

// Esegui il logout
  logout(): void {
    sessionStorage.removeItem('userNickname');
    sessionStorage.removeItem('sessionId');
    console.log(sessionStorage.getItem('isAdmin'));
    sessionStorage.setItem('isAdmin', String(false));
    this.router.navigate(['/auth']);
  }

}