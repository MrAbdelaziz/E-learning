package com.cteaching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cteaching.dto.FormationDto;
import com.cteaching.dto.QuizDto;
import com.cteaching.model.Formation;
import com.cteaching.model.Quiz;
import com.cteaching.model.Tuteur;
import com.cteaching.repositories.FormationRepository;
import com.cteaching.repositories.QuizRepository;

import java.util.List;

@Service
public class QuizService{

    private QuizRepository quizRepository;
    private FormationRepository cursoRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, FormationRepository cursoRepository) {
        this.quizRepository = quizRepository;
        this.cursoRepository =cursoRepository;
    }

    public void create(Long id_curso,QuizDto cursoDto) throws Exception{
    	String titreQuiz = cursoDto.getTitreQuiz();
        String descriptionQuiz = cursoDto.getDescriptionQuiz();
        int nbrQuestions = cursoDto.getNbrquestionsQuiz();
      //  Formation formation = cursoDto.getFormation();
        Formation formation = cursoRepository.findById(id_curso).get();
        
    	try {
        Quiz quiz = new Quiz(formation,titreQuiz,descriptionQuiz,nbrQuestions);
        quizRepository.save(quiz);
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        	System.out.println(titreQuiz);
        	System.out.println(descriptionQuiz);
        	System.out.println(nbrQuestions);
        	System.out.println(formation.getTitreFormation());
        }
    }

}
