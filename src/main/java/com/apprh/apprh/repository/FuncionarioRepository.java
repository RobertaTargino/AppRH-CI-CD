package com.apprh.apprh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apprh.apprh.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
