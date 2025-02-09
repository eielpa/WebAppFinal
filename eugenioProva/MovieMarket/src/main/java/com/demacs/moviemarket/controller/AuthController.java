package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.persistence.model.User;
import com.demacs.moviemarket.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = userService.registerUser(user);
        if ("USER_SAVED".equals(result)) {
            return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
        } else {

            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.loginUser(email, password);

        if (user == null) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

        if(email.equals("admin@gmail.com") && password.equals("admin")) {
            user.setAdmin(true);
        }
        else {
            user.setAdmin(false);
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("sessionId", session.getId());
        req.getServletContext().setAttribute(session.getId(), session);

        return ResponseEntity.ok().body(
                "{\"sessionId\":\"" + session.getId() +
                        "\", \"isAdmin\": " + user.getAdmin() +
                        ", \"nickname\": \"" + user.getId() + "\"}"
        );
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest req, @RequestParam String sessionId) {
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);

        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                // Creiamo un oggetto JSON con id e isAdmin
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("id", user.getId());
                userInfo.put("isAdmin", user.getAdmin()); // Assicurati che User abbia questo metodo

                return new ResponseEntity<>(userInfo, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
    }



    @GetMapping("/checkLogin")
    public ResponseEntity<Boolean> checkLoggedIn(HttpServletRequest req, @RequestParam String sessionId) {
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        boolean isLoggedIn = session != null && session.getAttribute("user") != null;
        return new ResponseEntity<>(isLoggedIn, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            return new ResponseEntity<>("Logout successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No active session", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Map<String, String>> updatePassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");

        User user = userService.getUserByEmail(email);

        user.setPassword(newPassword);

        userService.updatePassword(email, newPassword);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Password aggiornata con successo");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
