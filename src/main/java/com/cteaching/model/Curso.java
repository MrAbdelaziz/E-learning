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
@Table(name = "curso")
public class Curso {

    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nomCurso;

    @Column(name = "descripcion")
    private String descripcionCurso;

    @Column(name = "detalle")
    private String detalleCurso;

    @Column(name = "dificultad")
    private String dificultadCurso;

    @Column(name = "url")
    private String urlCurso;

    private String imgurl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profesor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profesor profesor;

    
    
    public Curso() {
		super();
	}

	public Curso(String nomCurso, String descripcionCurso, String detalleCurso, String dificultadCurso, String urlCurso, String imgurl, Profesor profesor) {
        this.nomCurso = nomCurso;
        this.descripcionCurso = descripcionCurso;
        this.detalleCurso = detalleCurso;
        this.dificultadCurso = dificultadCurso;
        this.urlCurso = urlCurso;
        this.imgurl = imgurl;
        this.profesor = profesor;
    }

	public Long getId_curso() {
		return id_curso;
	}

	public void setId_curso(Long id_curso) {
		this.id_curso = id_curso;
	}

	public String getNomCurso() {
		return nomCurso;
	}

	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}

	public String getDescripcionCurso() {
		return descripcionCurso;
	}

	public void setDescripcionCurso(String descripcionCurso) {
		this.descripcionCurso = descripcionCurso;
	}

	public String getDetalleCurso() {
		return detalleCurso;
	}

	public void setDetalleCurso(String detalleCurso) {
		this.detalleCurso = detalleCurso;
	}

	public String getDificultadCurso() {
		return dificultadCurso;
	}

	public void setDificultadCurso(String dificultadCurso) {
		this.dificultadCurso = dificultadCurso;
	}

	public String getUrlCurso() {
		return urlCurso;
	}

	public void setUrlCurso(String urlCurso) {
		this.urlCurso = urlCurso;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
    
    
    
}
