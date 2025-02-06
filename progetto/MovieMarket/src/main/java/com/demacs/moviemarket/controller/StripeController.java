package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.service.PersonalLibraryService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stripe")
@CrossOrigin(origins = "http://localhost:4200")
public class StripeController {

    @Autowired
    private PersonalLibraryService personalLibraryService;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody Map<String, Object> request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        String productName = (String) request.get("productName");
        String userNickname = (String) request.get("userNickname");

        // Recupera l'id come oggetto Number e convertilo in stringa
        Object idProvaObj = request.get("idProva");
        int idProva = (int) request.get("idProva");

        String encodedProductName = URLEncoder.encode(productName, StandardCharsets.UTF_8);
        String encodedUserNickname = URLEncoder.encode(userNickname, StandardCharsets.UTF_8);

        // Imposta i parametri della sessione di checkout
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                // Includi idProva nella successUrl se necessario:
                .setSuccessUrl("http://localhost:4200/payment-success?movie=" + encodedProductName
                        + "&user=" + encodedUserNickname + "&idProva=" + idProva)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("eur")
                                                .setUnitAmount(1000L)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(productName)
                                                                .build()
                                                )
                                                .build()
                                )
                                .setQuantity(1L)
                                .build()
                )
                .build();

        Session session = Session.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("checkoutUrl", session.getUrl());
        return response;
    }

}