package com.apprh.apprh.filter;

import java.io.IOException;

import com.apprh.apprh.model.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*") // Filtra as requisições
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // Libera as páginas de login
        if (uri.endsWith("login") || uri.endsWith("login.html")) {
            chain.doFilter(request, response);
            return;
        }

        // Verifica se existe uma sessão ativa com usuário logado
        HttpSession session = req.getSession(false);
        User usuario = (session != null) ? (User) session.getAttribute("user") : null;

        // Redireciona o usuario para login 
        if (usuario == null) {
            resp.sendRedirect("login.html");
            return;
        }

        // Verifica ose é user ou adm
        String role = usuario.getRole(); 

        if ("USER".equals(role)) {

            // Usuário comum só pode listar funcionários e fazer logout
            if (uri.contains("listarFuncionarios") || uri.contains("logout")) {
                chain.doFilter(request, response);
            } else {
                // Abre a listagem de funcionários
                resp.sendRedirect("listarFuncionarios.html");
            }

        } else if ("ADMIN".equals(role)) {

            // Administrador tem acesso total
            chain.doFilter(request, response);

        } else {

            resp.sendRedirect("login.html");
        }
    }
}
