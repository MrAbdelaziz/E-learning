package com.ezlearning.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @Column(name = "profesor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profesor;
    @Column(name = "nombre")
    private String nomProfesor;
    @Column(name = "apellido")
    private String apeProfesor;
    @Column(name = "correo")
    private String correoProfesor;
    @Column(name = "descripcion")
    private String descProfesor;
    @Column(name = "detalle")
    private String detalleProfesor;
    private String imgurl;


    public Profesor() {
		super();
	}

	public Profesor(String nomProfesor, String apeProfesor, String correoProfesor, String descProfesor, String imgurl) {
        this.nomProfesor = nomProfesor;
        this.apeProfesor = apeProfesor;
        this.correoProfesor = correoProfesor;
        this.descProfesor = descProfesor;
        this.imgurl = imgurl;
    }

    public Profesor(String detalleProfesor) {
        this.detalleProfesor = detalleProfesor;
    }

	public Long getId_profesor() {
		return id_profesor;
	}

	public void setId_profesor(Long id_profesor) {
		this.id_profesor = id_profesor;
	}

	public String getNomProfesor() {
		return nomProfesor;
	}

	public void setNomProfesor(String nomProfesor) {
		this.nomProfesor = nomProfesor;
	}

	public String getApeProfesor() {
		return apeProfesor;
	}

	public void setApeProfesor(String apeProfesor) {
		this.apeProfesor = apeProfesor;
	}

	public String getCorreoProfesor() {
		return correoProfesor;
	}

	public void setCorreoProfesor(String correoProfesor) {
		this.correoProfesor = correoProfesor;
	}

	public String getDescProfesor() {
		return descProfesor;
	}

	public void setDescProfesor(String descProfesor) {
		this.descProfesor = descProfesor;
	}

	public String getDetalleProfesor() {
		return detalleProfesor;
	}

	public void setDetalleProfesor(String detalleProfesor) {
		this.detalleProfesor = detalleProfesor;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
    
    
    
    
    
    
    
    
}
