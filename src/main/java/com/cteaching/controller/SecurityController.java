package com.cteaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cteaching.model.Formation;
import com.cteaching.model.Progression;
import com.cteaching.model.Tuteur;
import com.cteaching.model.User;
import com.cteaching.repositories.FormationRepository;
import com.cteaching.repositories.ProgressionRepository;
import com.cteaching.repositories.TuteurRepository;
import com.cteaching.repositories.UserRepository;
import com.cteaching.services.UserServiceImpl;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class SecurityController {

    private UserRepository userRepository;
    private ProgressionRepository matriculaRepository;
    private UserServiceImpl userService;
    private TuteurRepository profesorRepository;
    private FormationRepository cursoRepository;

    @Autowired
    public SecurityController(UserRepository userRepository, ProgressionRepository matriculaRepository, UserServiceImpl userService, FormationRepository cursoRepository, TuteurRepository profesorRepository) {
        this.userRepository = userRepository;
        this.matriculaRepository = matriculaRepository;
        this.userService = userService;
        this.cursoRepository=cursoRepository;
        this.profesorRepository=profesorRepository;
    }

    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication, Model model) {
        try {
            String currentUsername = authentication.getName();
            User user = userRepository.findByUsername(currentUsername);
            List<Progression> matriculas = matriculaRepository.findAllByUsuario(user);
            int numCursos = matriculas.size();
            model.addAttribute("user", user);
            model.addAttribute("matriculas", matriculas);
            model.addAttribute("numCursos", numCursos);
            
           Tuteur profesorActual = profesorRepository.findByemailTuteur(user.getEmail());
            if(profesorActual !=null) {
                   String isexsist ="oui";
                   List<Formation> cursos = cursoRepository.findAllByTuteur(profesorActual);
                  model.addAttribute("cursos", cursos);
	         	  model.addAttribute("isexsist", isexsist);
	         	  model.addAttribute("profesor", profesorActual);
            }
            
            return "user/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/user/edit/{id_user}")
    public String getForEdit(@PathVariable Long id_user, Authentication authentication, Model model) {

        try {
            String currentusername = authentication.getName();
            User current = userRepository.findById(id_user).get();
            if (currentusername.equals(current.getUsername())) {
            model.addAttribute(current);
            return "user/user-edit";
            } else {
                throw new Exception("Error de autenticacion");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/user/edit/{id_user}")
    public String updateUser(@PathVariable Long id_user, Authentication authentication, User user, Model model) {

        try {
            User current = userRepository.findById(id_user).get();
            String currentusername = authentication.getName();
            if (currentusername.equals(current.getUsername())) {
                current.setNom(user.getNom());
                current.setPrenom(user.getPrenom());
                current.setEmail(user.getEmail());
                current.setImgurl(user.getImgurl());
                userService.update(current);

                return "redirect:/profile";
            } else {
                throw new Exception("Error de autenticacion");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/user/patch/{id_user}")
    public String patchUser(@PathVariable Long id_user, Authentication authentication, User user, Model model) {

        try {
            User current = userRepository.findById(id_user).get();
            String currentusername = authentication.getName();
            if (currentusername.equals(current.getUsername())) {
                current.setDetail(user.getDetail());
                userService.patch(current);

                return "redirect:/profile";
            } else {
                throw new Exception("Error de autenticacion");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
