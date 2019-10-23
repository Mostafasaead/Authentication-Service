package com.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("email")
	private String email;
	@JsonProperty("msisdn")
	private String msisdn;
	@JsonProperty("wrongTrials")
	private int wrongTrials;
	@JsonProperty("validationQuestion")
	private String validationQuestion;
	@JsonProperty("answer")
	private String answer;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getWrongTrials() {
		return wrongTrials;
	}

	public void setWrongTrials(int wrongTrials) {
		this.wrongTrials = wrongTrials;
	}

	public String getValidationQuestion() {
		return validationQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setValidationQuestion(String validationQuestion) {
		this.validationQuestion = validationQuestion;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
