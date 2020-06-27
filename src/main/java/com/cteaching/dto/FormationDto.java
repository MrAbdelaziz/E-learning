package com.cteaching.dto;

import com.cteaching.model.Tuteur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormationDto {
    private String titreFormation;
    private String descriptionFormation;
    private String difficulteFormation;
    private String detailFormation;
    private String urlFormation;
    private String imgurl;
    private Tuteur tuteur;
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
	public String getDifficulteFormation() {
		return difficulteFormation;
	}
	public void setDifficulteFormation(String difficulteFormation) {
		this.difficulteFormation = difficulteFormation;
	}
	public String getDetailFormation() {
		return detailFormation;
	}
	public void setDetailFormation(String detailFormation) {
		this.detailFormation = detailFormation;
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
