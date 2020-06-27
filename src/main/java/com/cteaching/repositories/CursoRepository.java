package com.cteaching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cteaching.model.Curso;
import com.cteaching.model.Profesor;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNomCurso(String nombre);
    List<Curso> findAllByProfesor(Profesor profesor);
}
