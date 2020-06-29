package com.cteaching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "progression")
public class Progression {
    @Id
    @Column(name = "progression_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progression_id;

    @Column(name = "dateins")
    private LocalDate date_progression;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;
    
    public Progression(LocalDate fecha_matricula, User usuario, Formation curso) {
        this.date_progression = fecha_matricula;
        this.usuario = usuario;
        this.formation = curso;
    }
    
    public Progression() {
		super();
	}

	public Long getProgression_id() {
		return progression_id;
	}

	public void setProgression_id(Long progression_id) {
		this.progression_id = progression_id;
	}

	public LocalDate getDate_progression() {
		return date_progression;
	}

	public void setDate_progression(LocalDate date_progression) {
		this.date_progression = date_progression;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
    
    
    
    
}
