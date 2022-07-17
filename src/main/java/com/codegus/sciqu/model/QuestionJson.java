package com.codegus.sciqu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionJson {

	@JsonProperty("question")
	String question;
	
	@JsonProperty("distractor3")
	String optionA;
	
	@JsonProperty("distractor1")
	String optionB;
	
	@JsonProperty("distractor2")
	String optionC;
	
	public QuestionJson(String question, String optionA, String optionB, String optionC, String optionD,
			String explanation) {
		super();
		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.explanation = explanation;
	}
	
	public QuestionJson() {
		
	}

	@Override
	public String toString() {
		return "QuestionJson [question=" + question + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC="
				+ optionC + ", optionD=" + optionD + ", explanation=" + explanation + "]";
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@JsonProperty("correct_answer")
	String optionD;
	
	@JsonProperty("support")
	String explanation;
}
