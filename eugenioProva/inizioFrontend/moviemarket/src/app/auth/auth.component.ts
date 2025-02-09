import { Component, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginService } from '../services/login.service';
import { RegistrationService, User } from '../services/registration.service';
import {
  trigger,
  transition,
  style,
  animate
} from '@angular/animations';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
  animations: [
    trigger('formTransition', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateX(100%)' }),
        animate('800ms ease-out', style({ opacity: 1, transform: 'translateX(0)' }))
      ]),
      transition(':leave', [
        animate('800ms ease-out', style({ opacity: 0, transform: 'translateX(-100%)' }))
      ])
    ])
  ]
})
export class AuthComponent implements AfterViewInit {
  isLoginMode = true; // Modalità login o registrazione
  email = "";
  password = "";
  passwordVisible = false;
  errorMessage = "";

  // Variabile per la password del form di registrazione
  regPasswordVisible = false;

  user: User = {
    id: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    dob: ''
  };

  registrationMessage = "";

  constructor(
      private loginService: LoginService,
      private registrationService: RegistrationService,
      private router: Router
  ) {}

  ngAfterViewInit() {
    // Imposta l'altezza iniziale in base al form di login
    setTimeout(() => {
      this.updateWrapperHeight();
    }, 0);
  }

  toggleMode() {
    this.isLoginMode = !this.isLoginMode;
    this.errorMessage = "";
    this.registrationMessage = "";
    // Aggiorna l'altezza dopo un breve delay per permettere all'animazione di attivarsi
    setTimeout(() => {
      this.updateWrapperHeight();
    }, 50);
  }

  updateWrapperHeight() {
    const wrapper = document.querySelector('.auth-wrapper') as HTMLElement;
    // Seleziona il form attivo in base alla modalità
    const activeForm = document.querySelector('.auth-form.' + (this.isLoginMode ? 'login' : 'register')) as HTMLElement;
    if (wrapper && activeForm) {
      // Aggiungiamo qualche pixel extra se necessario (qui 60px per tener conto del padding)
      wrapper.style.height = activeForm.offsetHeight + 60 + 'px';
    }
  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
  }

  toggleRegPasswordVisibility() {
    this.regPasswordVisible = !this.regPasswordVisible;
  }

  preventCopyPaste(event: KeyboardEvent) {
    if (event.ctrlKey && (event.key === 'c' || event.key === 'v')) {
      event.preventDefault();
    }
  }

  login() {
    if (!this.email || !this.password) {
      this.errorMessage = "Inserisci email e password";
      return;
    }

    this.loginService.login(this.email, this.password).subscribe({
      next: (response) => {
        sessionStorage.setItem('sessionId', response.sessionId);
        sessionStorage.setItem('isAdmin', response.isAdmin.toString());
        sessionStorage.setItem('userNickname', response.nickname.toString());
        console.log(sessionStorage.getItem('userNickname'));
        this.router.navigate(['']);
      },
      error: () => {
        this.errorMessage = "Credenziali non valide";
      }
    });

  }

  register() {
    if (!this.user.id || !this.user.firstName || !this.user.lastName || !this.user.email || !this.user.password || !this.user.dob) {
      this.registrationMessage = "Compila tutti i campi obbligatori";
      return;
    }

    this.registrationService.registerUser(this.user).subscribe({
      next: (response) => {
        if (response === 'Registration successful') {
          alert('Registrazione completata con successo! Ora verrai reindirizzato alla pagina di login.');
          this.isLoginMode = true;
          setTimeout(() => { this.updateWrapperHeight(); }, 50);
        } else {
          this.registrationMessage = `Errore: ${response}`;
        }
      },
      error: () => {
        this.registrationMessage = 'Errore durante la registrazione. Riprova.';
      }
    });
  }


  recoverPassword() {
    this.router.navigate(["/recupero-password"]);
  }
}
