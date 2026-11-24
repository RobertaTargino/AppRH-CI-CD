package com.apprh.apprh.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apprh.apprh.model.User;
import com.apprh.apprh.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private AuthService authService;

    // Teste 1 - Login com senha correta
    @Test
    void deveRealizarLoginComSenhaCorreta() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));

        when(userRepo.findByUsername("admin"))
            .thenReturn(Optional.of(user));

        User resultado = authService.login("admin", "123456");

        assertEquals(user, resultado);
    }

    // Teste 2 - Login com senha incorreta
    @Test
    void deveRejeitarLoginComSenhaIncorreta() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));

        when(userRepo.findByUsername("admin"))
            .thenReturn(Optional.of(user));

        User resultado = authService.login("admin", "senhaerrada");

        assertNull(resultado);
    }
}