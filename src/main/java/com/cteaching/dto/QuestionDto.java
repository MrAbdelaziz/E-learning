package com.cteaching.dto;


import com.cteaching.model.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDto {
	    private String text;
	    private Quiz quiz;
	    
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public Quiz getQuiz() {
			return quiz;
		}
		public void setQuiz(Quiz quiz) {
			this.quiz = quiz;
		}
	    
    
}

