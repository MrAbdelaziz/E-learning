package com.cteaching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import com.cteaching.model.Formation;
import com.cteaching.model.User;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgressionDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_progression;
    private User user;
    private Formation curso;
    
	public Date getDate_progression() {
		return date_progression;
	}
	public void setDate_progression(Date date_progression) {
		this.date_progression = date_progression;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Formation getCurso() {
		return curso;
	}
	public void setCurso(Formation curso) {
		this.curso = curso;
	}
    



}
