package com.apprh.apprh.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apprh.apprh.model.User;
import com.apprh.apprh.repository.UserRepository;

@Service
public class AuthService {

    @Autowired 
    private UserRepository userRepo;

    // Verifica os dados no login
    public User login(String username, String password) {

        return userRepo.findByUsername(username)
                .filter(user -> BCrypt.checkpw(password, user.getPassword()))
                .orElse(null);
    }

    // Cadastra um usuário com a senha criptografada
    public void register(String username, String password, String role) {

        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));

        User user = new User();
        user.setUsername(username);
        user.setPassword(hash);
        user.setRole(role);
        userRepo.save(user);
    }
}