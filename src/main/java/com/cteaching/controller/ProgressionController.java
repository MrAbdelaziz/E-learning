package com.cteaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cteaching.model.Formation;
import com.cteaching.model.User;
import com.cteaching.repositories.FormationRepository;
import com.cteaching.repositories.UserRepository;
import com.cteaching.services.ProgressionService;

@Controller
@RequestMapping("/progression")
@PreAuthorize("hasRole('ROLE_USER')")
public class ProgressionController {

    private ProgressionService matriculaService;
    private UserRepository userRepository;
    private FormationRepository cursoRepository;

    @Autowired
    public ProgressionController(ProgressionService matriculaService, UserRepository userRepository, FormationRepository cursoRepository) {
        this.matriculaService = matriculaService;
        this.userRepository = userRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping("/save/{id_curso}")
    public String saveMatricula(@PathVariable Long id_curso, Authentication authentication, Model model) {
        try {
            String username = authentication.getName();
            matriculaService.createMatricula(id_curso, username);
            User user = userRepository.findByUsername(username);
            Formation curso = cursoRepository.findById(id_curso).get();
            model.addAttribute("curso", curso);
            model.addAttribute("user", user);
            return "matricula-success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
