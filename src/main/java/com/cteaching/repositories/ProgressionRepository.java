package com.cteaching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cteaching.model.Formation;
import com.cteaching.model.Progression;
import com.cteaching.model.User;

import java.util.List;

public interface ProgressionRepository extends JpaRepository<Progression, Long> {

    List<Progression> findAllByFormation(Formation curso);
    List<Progression> findAllByUsuario(User user);
    Progression findByFormationAndUsuario(Formation curso, User user);
}
