package com.cteaching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cteaching.model.Curso;
import com.cteaching.model.Matricula;
import com.cteaching.model.User;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findAllByCurso(Curso curso);
    List<Matricula> findAllByUsuario(User user);
    Matricula findByCursoAndUsuario(Curso curso, User user);
}
