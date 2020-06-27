package com.cteaching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cteaching.model.Formation;
import com.cteaching.model.Tuteur;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
	
    Formation findByTitreFormation(String titre);
    List<Formation> findAllByTuteur(Tuteur tuteur);
}
