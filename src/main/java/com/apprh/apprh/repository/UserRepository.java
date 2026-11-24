
package com.apprh.apprh.repository;

// Importa a classe User
import com.apprh.apprh.model.User;

// Importa o JpaRepository para acessar o banco de dados
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Interface responsável pelas operações com usuários
public interface UserRepository extends JpaRepository<User, String> {

    // Busca um usuário pelo nome de usuário
    Optional<User> findByUsername(String username);
}
