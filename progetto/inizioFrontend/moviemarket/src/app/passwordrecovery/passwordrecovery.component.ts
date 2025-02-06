import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-recupero-password',
  imports: [RouterLink],  // RouterLink deve essere importato qui
  templateUrl: './passwordrecovery.component.html',
  standalone: true,
  styleUrls: ['./passwordrecovery.component.css']
})
export class RecuperoPasswordComponent {
  title = "Recupero della password";

  submitRecoveryForm() {
    // Gestisci il processo di recupero password (puoi aggiungere la logica qui)
    alert("Processo di recupero password in corso...");
  }
}
