package com.apprh.apprh.controller;

import com.apprh.apprh.model.User;
import com.apprh.apprh.service.AuthService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController 
@RequestMapping("/auth") // Define a rota principal
public class AuthController {

    @Autowired //autenticação
    private AuthService auth;

    //  dados de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username,
            @RequestParam String password, HttpSession session) {

        User user = auth.login(username, password);

        if (user != null) {
            session.setAttribute("user", user); // Guarda os dados do user 
            return ResponseEntity.ok(user.getRole()); // Retorna se é user ou adm
        }

        // Retorna erro se os dados estiverem errados
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciais inválidas");
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session,
            HttpServletResponse response) throws IOException {

        session.invalidate(); // Encerra a sessão
        response.sendRedirect("/login.html"); // Volta para a pagina de login

        return ResponseEntity.ok().build();
    }
}
