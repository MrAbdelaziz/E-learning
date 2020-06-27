package com.cteaching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cteaching.model.Tuteur;

public interface TuteurRepository extends JpaRepository<Tuteur, Long> {
}
