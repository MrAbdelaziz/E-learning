package com.cteaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cteaching.dto.TuteurDto;
import com.cteaching.model.Formation;
import com.cteaching.model.Progression;
import com.cteaching.model.Tuteur;
import com.cteaching.repositories.FormationRepository;
import com.cteaching.repositories.ProgressionRepository;
import com.cteaching.repositories.TuteurRepository;
import com.cteaching.services.TuteurService;

import java.util.List;

@Controller
@RequestMapping("/tuteurs")
public class TuteurController {

    private TuteurService profesorService;
    private TuteurRepository profesorRepository;
    private FormationRepository cursoRepository;
    private ProgressionRepository progressionRepository;
    @Autowired
    public TuteurController(TuteurService profesorService, TuteurRepository profesorRepository,
    		FormationRepository cursoRepository,ProgressionRepository progressionRepository) {
        this.profesorService = profesorService;
        this.profesorRepository = profesorRepository;
        this.cursoRepository = cursoRepository;
        this.progressionRepository =progressionRepository;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addProfesor(Model model) {
        model.addAttribute("profesor", new TuteurDto());
        return "profesores/profesor-add";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String saveProfesor(TuteurDto profesor) {
        profesorService.create(profesor);

        return "redirect:/tuteurs";
    }

    @GetMapping("/edit/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getProfesorForUpdate(@PathVariable Long id_profesor,
                                       Model model) {
        try {
        	Tuteur profesorActual = profesorRepository.findById(id_profesor).get();
            model.addAttribute("profesor", profesorActual);
            return "profesores/profesor-edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/update/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateProfesor(@PathVariable Long id_profesor,
    		Tuteur profesor, RedirectAttributes attributes, Model model){

        try {
        	Tuteur currentProfesor = profesorRepository.findById(id_profesor).get();
            currentProfesor.setNomTuteur(profesor.getNomTuteur());
            currentProfesor.setPrenomTuteur(profesor.getPrenomTuteur());
            currentProfesor.setEmailTuteur(profesor.getEmailTuteur());
            currentProfesor.setDescriptionTuteur(profesor.getDescriptionTuteur());
            currentProfesor.setImgurl(profesor.getImgurl());

            profesorService.update(profesor,id_profesor);
            attributes.addAttribute("id_profesor", id_profesor);

            return "redirect:/tuteurs/{id_profesor}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/patch/{id_profesor}")
    public String patchProfesor(@PathVariable Long id_profesor, Tuteur profesor, RedirectAttributes attributes, Model model) {

        try {
        	Tuteur current = profesorRepository.findById(id_profesor).get();
            current.setDetailTuteur(profesor.getDetailTuteur());
            profesorService.patch(current);

            attributes.addAttribute("id_profesor", id_profesor);
            return "redirect:/tuteurs/{id_profesor}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getProfesoresList(Model model) {
        List<Tuteur> profesores = profesorService.getAll();
        model.addAttribute("profesores", profesores);
        return "profesores/profesores";
    }

    @GetMapping("/delete/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProfesor(@PathVariable Long id_profesor, Model model) {
        try {
        	Tuteur profesorActual = profesorRepository.findById(id_profesor).get();
            
        	
        	//delete cascade
        	List<Formation> listform = cursoRepository.findAllByTuteur(profesorActual);
       	 for(Formation formation : listform){
       		 List<Progression> listprog = progressionRepository.findAllByFormation(formation);
       		 for(Progression progression : listprog){
       			 progressionRepository.delete(progression);
       		 }
       		cursoRepository.delete(formation);
    	    }
        	
        	profesorService.delete(profesorActual);
            return "redirect:/tuteurs";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getProfesorDetail(@PathVariable Long id_profesor, Model model) {
        try {
        	Tuteur profesor = profesorRepository.findById(id_profesor).get();
            model.addAttribute("profesor", profesor);
            List<Formation> cursos = cursoRepository.findAllByTuteur(profesor);
            model.addAttribute("cursos", cursos);
            return "profesores/profesor-detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
