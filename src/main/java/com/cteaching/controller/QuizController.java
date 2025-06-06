package com.cteaching.controller;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cteaching.dto.FormationDto;
import com.cteaching.dto.QuestionDto;
import com.cteaching.dto.QuizDto;
import com.cteaching.model.Formation;
import com.cteaching.model.Progression;
import com.cteaching.model.Quiz;
import com.cteaching.model.Tuteur;
import com.cteaching.model.User;
import com.cteaching.repositories.FormationRepository;
import com.cteaching.repositories.ProgressionRepository;
import com.cteaching.repositories.QuizRepository;
import com.cteaching.repositories.TuteurRepository;
import com.cteaching.repositories.UserRepository;
import com.cteaching.services.FormationService;
import com.cteaching.services.QuizService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController{

    private FormationService cursoService;
    private FormationRepository cursoRepository;
    private ProgressionRepository matriculaRepository;
    private UserRepository userRepository;
    private TuteurRepository profesorRepository;
    private QuizService quizservice;
    private QuizRepository quizRepository;

    @Autowired
    public QuizController(FormationService cursoService, FormationRepository cursoRepository,
    		ProgressionRepository matriculaRepository, UserRepository userRepository, TuteurRepository profesorRepository, QuizService quizservice, QuizRepository quizRepository) {
        super();
        this.cursoService = cursoService;
        this.cursoRepository = cursoRepository;
        this.matriculaRepository = matriculaRepository;
        this.userRepository = userRepository;
        this.profesorRepository = profesorRepository;
        this.quizservice=  quizservice;
        this.quizRepository=quizRepository;
    }

  /*  @GetMapping("/add/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_USER')")
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
    @PreAuthorize("hasRole('ROLE_USER')")
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
    
    @GetMapping("/{id_curso}/students")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getstudebts(@PathVariable Long id_curso, Authentication authentication, Model model) {
    	
    	          //List<Formation> cursos = cursoService.getAll();
                 //model.addAttribute("cursos", cursos);
                //return "formations/formations";
    	       //List<User> profesores = profesorService.getAll();
              //model.addAttribute("profesores", profesores);
    	
    	Formation cursoActual = cursoRepository.findById(id_curso).get();
    	List<Progression> listprog = matriculaRepository.findAllByFormation(cursoActual);
    	ArrayList<User> userlist = new ArrayList<User>();
  		 for(Progression progression : listprog){
  			userlist.add(progression.getUsuario());		 
  		 }
    	
  		model.addAttribute("userlist", userlist);
  		 return "formations/students";
    }
    */
   //update
    @GetMapping("/add/step1/{id_formation}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addquiz(@PathVariable Long id_formation, Model model) {
        try {
        	Formation formation = cursoRepository.findById(id_formation).get();
            model.addAttribute("quiz", new QuizDto());
            model.addAttribute("formation", formation);
            return "quiz/quiz-step1";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
    
    
    @PostMapping("/add/step1/{id_formation}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String saveCurso(@PathVariable Long id_formation, QuizDto quiz, Model model) {
        try {
        	Formation formation = cursoRepository.findById(id_formation).get();
        	quiz.setFormation(formation);
        	quizservice.create(id_formation,quiz);
            Quiz quiz2= quizRepository.findByformation(formation);

            return "redirect:/quiz/add/step2/"+quiz2.getQuiz_id();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }

    }
    
    
    
    @GetMapping("/add/step2/{quiz_id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addquizsetp2(@PathVariable Long quiz_id, Model model) {
        try {
        	//Formation formation = cursoRepository.findById(id_formation).get();
            //model.addAttribute("quiz", new QuizDto());
            //model.addAttribute("formation", formation);
        	Quiz quiz= quizRepository.findById(quiz_id).get();
      
        	model.addAttribute("question", new QuestionDto());
            model.addAttribute("quiz", quiz);
            return "quiz/quiz-step2";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
    
    
    
    
    

    @RequestMapping("/add/step3")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addrepsetp3(@RequestParam("q1") String q1,@RequestParam("q2") String q2, @RequestParam("q3") String q3,@RequestParam("q4") String q4,Model model) {
        try {
        	//Formation formation = cursoRepository.findById(id_formation).get();
            //model.addAttribute("quiz", new QuizDto());
            //model.addAttribute("formation", formation);
      
        	model.addAttribute("q1", q1);
        	model.addAttribute("q2", q2);
        	model.addAttribute("q3", q3);
        	model.addAttribute("q4", q4);
            return "quiz/quiz-step3";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
    
    
    
    
    /*@PostMapping("/add/step2/{quiz_id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String savestep2(@PathVariable Long quiz_id, QuestionDto question, Model model) {
        try {
        	//Quiz quiz2= quizRepository.findById(quiz_id).get();
        	//question.setQuiz(quiz2);
        	//quizservice.create(id_formation,quiz);
            //Quiz quiz2= quizRepository.findByformation(formation);

            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }

    }*/
    
}
