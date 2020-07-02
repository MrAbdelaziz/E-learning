package com.cteaching.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cteaching.model.Formation;
import com.cteaching.model.Tuteur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizDto {
	    private String titreQuiz;
	    private String descriptionQuiz;
	    private Formation formation;
	    private int nbrquestionsQuiz;
	    
		public String getTitreQuiz() {
			return titreQuiz;
		}
		public void setTitreQuiz(String titreQuiz) {
			this.titreQuiz = titreQuiz;
		}
		public String getDescriptionQuiz() {
			return descriptionQuiz;
		}
		public void setDescriptionQuiz(String descriptionQuiz) {
			this.descriptionQuiz = descriptionQuiz;
		}


		public int getNbrquestionsQuiz() {
			return nbrquestionsQuiz;
		}
		public void setNbrquestionsQuiz(int nbrquestionsQuiz) {
			this.nbrquestionsQuiz = nbrquestionsQuiz;
		}
		public Formation getFormation() {
			return formation;
		}
		public void setFormation(Formation formation) {
			this.formation = formation;
		}
	    
    
    
}

