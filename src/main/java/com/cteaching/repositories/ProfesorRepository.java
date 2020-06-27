package com.cteaching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cteaching.model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
