package com.cteaching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cteaching.dto.MatriculaDto;
import com.cteaching.model.Curso;
import com.cteaching.model.Matricula;
import com.cteaching.model.User;
import com.cteaching.repositories.CursoRepository;
import com.cteaching.repositories.MatriculaRepository;
import com.cteaching.repositories.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class MatriculaService {

    private MatriculaRepository matriculaRepository;
    private CursoRepository cursoRepository;
    private UserRepository userRepository;

    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository, CursoRepository cursoRepository, UserRepository userRepository) {
        this.matriculaRepository = matriculaRepository;
        this.cursoRepository = cursoRepository;
        this.userRepository = userRepository;
    }

    public void createMatricula(Long id_curso, String username) throws Exception {
        Curso curso = cursoRepository.findById(id_curso).get();
        User user = userRepository.findByUsername(username);

        if (null != matriculaRepository.findByCursoAndUsuario(curso, user)) {
            throw new Exception("Ya se encuentra matriculado en este curso");
        }
        LocalDate date = LocalDate.now();
        Matricula matricula = new Matricula(date, user, curso);
        matriculaRepository.save(matricula);
    }
}
