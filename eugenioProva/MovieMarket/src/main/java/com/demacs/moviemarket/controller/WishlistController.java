package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.persistence.model.Wishlist;
import com.demacs.moviemarket.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Modifica la firma del metodo per ricevere un body JSON con userId e movieId
    @PostMapping("/add")
    public ResponseEntity<?> addToWishlist(@RequestBody WishlistRequest request) {
        try {
            Wishlist wishlist = wishlistService.addToWishlist(request.getUserId(), request.getMovieId());
            return ResponseEntity.ok(wishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Long id) {
        try {
            wishlistService.removeFromWishlist(id);
            return ResponseEntity.ok("Film rimosso dalla wishlist.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlistByUser(@PathVariable String userId) {
        List<Wishlist> list = wishlistService.getWishlistByUser(userId);
        return ResponseEntity.ok(list);
    }
}