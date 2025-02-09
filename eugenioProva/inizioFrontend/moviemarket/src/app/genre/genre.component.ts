import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../services/movie.service';
import { CategoryService } from '../services/category.service';
import { MovieCardComponent } from "../moviecard/moviecard.component";
import { NgForOf } from "@angular/common";
import { NavbarComponent } from "../navbar/navbar.component";

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  standalone: true,
  imports: [
    MovieCardComponent,
    NgForOf,
    NavbarComponent
  ],
  styleUrls: ['./genre.component.css']
})
export class GenreComponent implements OnInit {
  genre: string | null = null; // Il nome del genere
  movies: any[] = [];
  selectedCategoryId: number | null = null;

  constructor(
      private route: ActivatedRoute,
      private movieService: MovieService,
      private categoryService: CategoryService,
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      // Recupera l'ID dalla rotta (nota: qui usiamo l'id e non il "name")
      this.selectedCategoryId = Number(params.get('name'));

      if (this.selectedCategoryId) {
        // Recupera il nome del genere dalla categoria
        this.categoryService.getCategoryNameById(this.selectedCategoryId).subscribe((categoryName: string) => {
          this.genre = categoryName;
        });

        // Recupera i film per la categoria
        this.movieService.getMoviesByCategory(this.selectedCategoryId).subscribe((data: any) => {
          this.movies = data;
        });
      }
    });
  }
}
