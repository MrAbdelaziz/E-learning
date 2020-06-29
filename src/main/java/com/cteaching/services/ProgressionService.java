package com.cteaching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cteaching.model.Formation;
import com.cteaching.model.Progression;
import com.cteaching.model.Tuteur;
import com.cteaching.model.User;
import com.cteaching.repositories.FormationRepository;
import com.cteaching.repositories.ProgressionRepository;
import com.cteaching.repositories.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class ProgressionService {

    private ProgressionRepository matriculaRepository;
    private FormationRepository cursoRepository;
    private UserRepository userRepository;

    @Autowired
    public ProgressionService(ProgressionRepository matriculaRepository, FormationRepository cursoRepository, UserRepository userRepository) {
        this.matriculaRepository = matriculaRepository;
        this.cursoRepository = cursoRepository;
        this.userRepository = userRepository;
    }

    public void createMatricula(Long id_curso, String username) throws Exception {
        Formation curso = cursoRepository.findById(id_curso).get();
        User user = userRepository.findByUsername(username);

        if (null != matriculaRepository.findByFormationAndUsuario(curso, user)) {
            throw new Exception("Ya se encuentra matriculado en este curso");
        }
        LocalDate date = LocalDate.now();
        Progression matricula = new Progression(date, user, curso);
        matriculaRepository.save(matricula);
    }
    
    
    public void delete(Progression progression){
    	matriculaRepository.delete(progression);
    }
}
