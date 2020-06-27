package com.cteaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cteaching.model.Formation;
import com.cteaching.model.Tuteur;
import com.cteaching.services.FormationService;
import com.cteaching.services.TuteurService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    private TuteurService profesorService;
    private FormationService cursoService;

    @Autowired
    public APIController(TuteurService profesorService, FormationService cursoService) {
        super();
        this.profesorService = profesorService;
        this.cursoService = cursoService;
    }

    /*@GetMapping("/profesores")
    public List<Tuteur> getAllProf() {
        return this.profesorService.getAll();
    }

    @GetMapping("/cursos")
    public List<Formation> getAllCurso() {
        return this.cursoService.getAll();
    }
    
    */
    
    @GetMapping("/tuteurs")
    public List<Tuteur> getAllProf() {
        return this.profesorService.getAll();
    }

    @GetMapping("/formations")
    public List<Formation> getAllCurso() {
        return this.cursoService.getAll();
    }
    
    
}
