package com.apprh.apprh.controller;

import com.apprh.apprh.model.Funcionario;
import com.apprh.apprh.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController // Define a classe como controlador REST
@RequestMapping("/funcionarios") // Define a rota principal
public class FuncionarioController {

    @Autowired // Injeta o repositório automaticamente
    private FuncionarioRepository funcionarioRepo;

    @GetMapping("/list") // Lista os funcionários ja cadastrados
    public List<Funcionario> list(HttpSession session) {
        return funcionarioRepo.findAll();
    }

    @PostMapping("/add") // Cadastra funcionários
    public void add(@RequestBody Funcionario funcionario) {
        funcionarioRepo.save(funcionario);
    }
}
