package com.cteaching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cteaching.dto.FormationDto;
import com.cteaching.model.Formation;
import com.cteaching.model.Tuteur;
import com.cteaching.repositories.FormationRepository;

import java.util.List;

@Service
public class FormationService{

    private FormationRepository cursoRepository;

    @Autowired
    public FormationService(FormationRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public void create(FormationDto cursoDto) throws Exception{
        if (null != cursoRepository.findByTitreFormation(cursoDto.getTitreFormation())) {
            throw new Exception("Ya existe un curso con el nombre " + cursoDto.getTitreFormation());
        }
        String nomCurso = cursoDto.getTitreFormation();
        String descCurso = cursoDto.getDescriptionFormation();
        String detalleCurso = cursoDto.getDetailFormation();
        String difCurso = cursoDto.getDifficulteFormation();
        String urlCurso = cursoDto.getUrlFormation();
        String imgurl = cursoDto.getImgurl();
        Tuteur profesor = cursoDto.getTuteur();
        Formation curso = new Formation(nomCurso, descCurso, detalleCurso, difCurso, urlCurso, imgurl, profesor);

        cursoRepository.save(curso);
    }

    public void update(Formation curso, Long id_curso) {
        Formation currentCurso = cursoRepository.findById(id_curso).get();

            currentCurso.setTitreFormation(curso.getTitreFormation());
            currentCurso.setDescriptionFormation(curso.getDescriptionFormation());
            currentCurso.setDetailFormation(curso.getDetailFormation());
            currentCurso.setDifficulteFormation(curso.getDifficulteFormation());
            currentCurso.setUrlFormation(curso.getUrlFormation());
            currentCurso.setImgurl(curso.getImgurl());
            currentCurso.setTuteur(curso.getTuteur());

            cursoRepository.save(currentCurso);

    }

    public void delete(Formation curso) { cursoRepository.delete(curso); }


    public List<Formation> getAll() {
        return cursoRepository.findAll();
    }

}
