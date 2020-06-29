package com.cteaching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cteaching.dto.TuteurDto;
import com.cteaching.model.Formation;
import com.cteaching.model.Progression;
import com.cteaching.model.Tuteur;
import com.cteaching.repositories.*;

import java.util.List;

@Service
public class TuteurService {

    private TuteurRepository profesorRepository;
    private ProgressionRepository progressionRepository;
    private FormationRepository formationrepository;
    
    @Autowired
    public TuteurService(TuteurRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public void create(TuteurDto profesotDto) {
        String nombre = profesotDto.getNom();
        String apellido = profesotDto.getPrenom();
        String correo = profesotDto.getEmail();
        String descripcion = profesotDto.getDescription();
        String imgurl = profesotDto.getImgurl();
        Tuteur profesor = new Tuteur(nombre, apellido, correo, descripcion, imgurl);

        profesorRepository.save(profesor);
    }

    public List<Tuteur> getAll() {
        return profesorRepository.findAll();
    }

    public void update(Tuteur profesor, Long id_prof) {
    	Tuteur currentProfesor = profesorRepository.findById(id_prof).get();

        currentProfesor.setNomTuteur(profesor.getNomTuteur());
        currentProfesor.setPrenomTuteur(profesor.getPrenomTuteur());
        currentProfesor.setEmailTuteur(profesor.getEmailTuteur());
        currentProfesor.setDescriptionTuteur(profesor.getDescriptionTuteur());
        currentProfesor.setImgurl(profesor.getImgurl());

        profesorRepository.save(currentProfesor);
    }

    public void patch(Tuteur profesor) {
    	Tuteur current = profesorRepository.findById(profesor.getTuteur_id()).get();

        current.setDetailTuteur(profesor.getDetailTuteur());

        profesorRepository.save(current);
    }

    public void delete(Tuteur profesor){
        profesorRepository.delete(profesor);
    }

}
