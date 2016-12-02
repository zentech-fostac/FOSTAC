package com.ir.service;

import java.util.List;

import com.ir.form.AssessmentAnswerCriteria;
import com.ir.model.AssessmentQuestion;

public interface AssessmentService {
	
	public List<AssessmentQuestion> getAssessmentQuestions(int courseType, int courseName);
	public String saveAssessment(List<AssessmentAnswerCriteria> assessmentAnswerCriteria);

}