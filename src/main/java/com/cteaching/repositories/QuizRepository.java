package com.cteaching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cteaching.model.Formation;
import com.cteaching.model.Quiz;


public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	Quiz findByformation(Formation formation);
   // List<Formation> findAllByTuteur(Tuteur tuteur);
}
