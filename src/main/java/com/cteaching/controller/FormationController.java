package com.cteaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cteaching.dto.FormationDto;
import com.cteaching.model.Formation;
import com.cteaching.model.Progression;
import com.cteaching.model.Tuteur;
import com.cteaching.model.User;
import com.cteaching.repositories.FormationRepository;
import com.cteaching.repositories.ProgressionRepository;
import com.cteaching.repositories.TuteurRepository;
import com.cteaching.repositories.UserRepository;
import com.cteaching.services.FormationService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class FormationController{

    private FormationService cursoService;
    private FormationRepository cursoRepository;
    private ProgressionRepository matriculaRepository;
    private UserRepository userRepository;
    private TuteurRepository profesorRepository;

    @Autowired
    public FormationController(FormationService cursoService, FormationRepository cursoRepository,
    		ProgressionRepository matriculaRepository, UserRepository userRepository, TuteurRepository profesorRepository) {
        super();
        this.cursoService = cursoService;
        this.cursoRepository = cursoRepository;
        this.matriculaRepository = matriculaRepository;
        this.userRepository = userRepository;
        this.profesorRepository = profesorRepository;
    }

    @GetMapping("/add/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCurso(@PathVariable Long id_profesor, Model model) {
        try {
            Tuteur current = profesorRepository.findById(id_profesor).get();
            model.addAttribute("curso", new FormationDto());
            model.addAttribute("profesor", current);
            return "formations/curso-add";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/add/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCurso(@PathVariable Long id_profesor, FormationDto curso, Model model) {
        try {
            Tuteur current = profesorRepository.findById(id_profesor).get();
            curso.setTuteur(current);
            cursoService.create(curso);
            return "redirect:/courses";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }

    }

    @GetMapping("/edit/{id_curso}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCursoForUpdate(@PathVariable Long id_curso, Model model) {
        try {
            Formation cursoActual = cursoRepository.findById(id_curso).get();
            model.addAttribute("curso", cursoActual);
            return "formations/curso-edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/edit/{id_profesor}/{id_curso}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateCurso(@PathVariable Long id_profesor, @PathVariable Long id_curso, Formation curso, Model model, RedirectAttributes attributes) {

        try {
            Tuteur currentProfesor = profesorRepository.findById(id_profesor).get();
            curso.setTuteur(currentProfesor);

            cursoService.update(curso, id_curso);
            attributes.addAttribute("id_curso", id_curso);

            return "redirect:/courses/{id_curso}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping
    public String getCursosList(Model model) {
        List<Formation> cursos = cursoService.getAll();
        model.addAttribute("cursos", cursos);
        return "formations/formations";
    }

    @GetMapping("/delete/{id_curso}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCurso(@PathVariable Long id_curso, Model model) {
        try {
        	Formation cursoActual = cursoRepository.findById(id_curso).get();
        	
        	
        	//delete cascade
       		 List<Progression> listprog = matriculaRepository.findAllByFormation(cursoActual);
       		 for(Progression progression : listprog){
       			matriculaRepository.delete(progression);
       		 }
       		
        	
        	
        	
        	
        	
            cursoService.delete(cursoActual);

            return "redirect:/courses";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/{id_curso}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCursoDetail(@PathVariable Long id_curso, Authentication authentication, Model model) {
        String username = authentication.getName();
        Boolean matriculado = false;
        try {
        	Formation curso = cursoRepository.findById(id_curso).get();
            User user = userRepository.findByUsername(username);
            if (null != matriculaRepository.findByFormationAndUsuario(curso, user)) {
                matriculado = true;
            }
            model.addAttribute("curso", curso);
            model.addAttribute("matriculado", matriculado);
            return "formations/curso-detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
