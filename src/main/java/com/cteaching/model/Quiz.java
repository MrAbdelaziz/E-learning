package com.cteaching.model;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "quiz")
public class Quiz {
	@Id
	@Column(name = "quiz_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quiz_id;
	
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Formation formation;

    @Column(name = "titre")
	private String titreQuiz;

    @Column(name = "description")
	private String descriptionQuiz;
    
    @Column(name = "nbrquestions")
    private int nbrquestionsQuiz;


	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Quiz(Formation formation, String titreQuiz, String descriptionQuiz, int nbrquestionsQuiz) {
		super();
		this.formation = formation;
		this.titreQuiz = titreQuiz;
		this.descriptionQuiz = descriptionQuiz;
		this.nbrquestionsQuiz = nbrquestionsQuiz;
	}





	public int getNbrquestionsQuiz() {
		return nbrquestionsQuiz;
	}





	public void setNbrquestionsQuiz(int nbrquestionsQuiz) {
		this.nbrquestionsQuiz = nbrquestionsQuiz;
	}





	public Long getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(Long quiz_id) {
		this.quiz_id = quiz_id;
	}



	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

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




}
