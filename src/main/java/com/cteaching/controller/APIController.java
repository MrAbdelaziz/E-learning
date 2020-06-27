package com.cteaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cteaching.model.Curso;
import com.cteaching.model.Profesor;
import com.cteaching.services.CursoService;
import com.cteaching.services.ProfesorService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    private ProfesorService profesorService;
    private CursoService cursoService;

    @Autowired
    public APIController(ProfesorService profesorService, CursoService cursoService) {
        super();
        this.profesorService = profesorService;
        this.cursoService = cursoService;
    }

    @GetMapping("/profesores")
    public List<Profesor> getAllProf() {
        return this.profesorService.getAll();
    }

    @GetMapping("/cursos")
    public List<Curso> getAllCurso() {
        return this.cursoService.getAll();
    }
}
