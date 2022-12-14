package com.coderscampus.AssignmentSubmissionApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	@JsonProperty("full_name")
private String full_name;
	@JsonProperty("username")
private String username;
	@JsonProperty("password")
private String password;
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String name) {
		this.full_name = name;
	}
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
	
	
}
