package com.cteaching.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formation")
public class Formation {

    @Id
    @Column(name = "formation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formation_id;

    @Column(name = "titre", nullable = false, unique = true)
    private String titreFormation;
    
    @Column(name = "description")
    private String descriptionFormation;

    @Column(name = "detail")
    private String detailFormation;
    
    @Column(name = "difficulte")
    private String difficulteFormation;
    
    @Column(name = "url")
    private String urlFormation;
    
    private String imgurl;
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tuteur_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tuteur tuteur;
    
    
    public Formation() {
		super();
	}

	public Formation(String nomCurso, String descripcionCurso, String detalleCurso, String dificultadCurso, String urlCurso, String imgurl, Tuteur profesor) {
        this.titreFormation = nomCurso;
        this.descriptionFormation = descripcionCurso;
        this.detailFormation = detalleCurso;
        this.difficulteFormation = dificultadCurso;
        this.urlFormation = urlCurso;
        this.imgurl = imgurl;
        this.tuteur = profesor;
    }

	public Long getFormation_id() {
		return formation_id;
	}

	public void setFormation_id(Long formation_id) {
		this.formation_id = formation_id;
	}

	public String getTitreFormation() {
		return titreFormation;
	}

	public void setTitreFormation(String titreFormation) {
		this.titreFormation = titreFormation;
	}

	public String getDescriptionFormation() {
		return descriptionFormation;
	}

	public void setDescriptionFormation(String descriptionFormation) {
		this.descriptionFormation = descriptionFormation;
	}

	public String getDetailFormation() {
		return detailFormation;
	}

	public void setDetailFormation(String detailFormation) {
		this.detailFormation = detailFormation;
	}

	public String getDifficulteFormation() {
		return difficulteFormation;
	}

	public void setDifficulteFormation(String difficulteFormation) {
		this.difficulteFormation = difficulteFormation;
	}

	public String getUrlFormation() {
		return urlFormation;
	}

	public void setUrlFormation(String urlFormation) {
		this.urlFormation = urlFormation;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Tuteur getTuteur() {
		return tuteur;
	}

	public void setTuteur(Tuteur tuteur) {
		this.tuteur = tuteur;
	}
	
	
	
    
}
