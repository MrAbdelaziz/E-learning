package com.cteaching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import com.cteaching.model.Curso;
import com.cteaching.model.User;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatriculaDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private User user;
    private Curso curso;
}
