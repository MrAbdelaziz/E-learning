package com.cteaching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tuteur")
public class Tuteur {

    @Id
    @Column(name = "tuteur_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tuteur_id;
    @Column(name = "nom")
    private String nomTuteur;
    @Column(name = "prenom")
    private String prenomTuteur;
    @Column(name = "email")
    private String emailTuteur;
    @Column(name = "description")
    private String descriptionTuteur;
    @Column(name = "detail")
    private String detailTuteur;
    private String imgurl;
    
    public Tuteur() {
		super();
	}

    
    public Tuteur(String nomProfesor, String apeProfesor, String correoProfesor, String descProfesor, String imgurl) {
        this.nomTuteur = nomProfesor;
        this.prenomTuteur = apeProfesor;
        this.emailTuteur = correoProfesor;
        this.descriptionTuteur = descProfesor;
        this.imgurl = imgurl;
    }

    public Tuteur(String detalleProfesor) {
        this.detailTuteur = detalleProfesor;
    }


	public Long getTuteur_id() {
		return tuteur_id;
	}


	public void setTuteur_id(Long tuteur_id) {
		this.tuteur_id = tuteur_id;
	}


	public String getNomTuteur() {
		return nomTuteur;
	}


	public void setNomTuteur(String nomTuteur) {
		this.nomTuteur = nomTuteur;
	}


	public String getPrenomTuteur() {
		return prenomTuteur;
	}


	public void setPrenomTuteur(String prenomTuteur) {
		this.prenomTuteur = prenomTuteur;
	}


	public String getEmailTuteur() {
		return emailTuteur;
	}


	public void setEmailTuteur(String emailTuteur) {
		this.emailTuteur = emailTuteur;
	}


	public String getDescriptionTuteur() {
		return descriptionTuteur;
	}


	public void setDescriptionTuteur(String descriptionTuteur) {
		this.descriptionTuteur = descriptionTuteur;
	}


	public String getDetailTuteur() {
		return detailTuteur;
	}


	public void setDetailTuteur(String detailTuteur) {
		this.detailTuteur = detailTuteur;
	}


	public String getImgurl() {
		return imgurl;
	}


	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
    
    
    
    
    
    
}
