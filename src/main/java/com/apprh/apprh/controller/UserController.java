package com.apprh.apprh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apprh.apprh.model.User;
import com.apprh.apprh.repository.UserRepository;
import com.apprh.apprh.service.AuthService;

@RestController // Define a classe como controlador REST
@RequestMapping("/users") // Rota principal dos usuários
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthService auth;

    // Lista todos os usuários cadastrados
    @GetMapping("/list")
    public List<User> list() {
        return userRepo.findAll();
    }

    // Cadastra um novo usuário
    @PostMapping("/add")
    public void add(@RequestBody User user) {
        auth.register(user.getUsername(), user.getPassword(), user.getRole());
    }
}
