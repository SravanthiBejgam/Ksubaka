package com.cinema.sravs.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "credit_id", "department", "gender", "id", "job", "name", "profile_path" })
public class Crew {

	@JsonProperty("credit_id")
	private String creditId;
	@JsonProperty("department")
	private String department;
	@JsonProperty("gender")
	private Integer gender;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("job")
	private String job;
	@JsonProperty("name")
	private String name;
	@JsonProperty("profile_path")
	private String profilePath;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("credit_id")
	public String getCreditId() {
		return creditId;
	}

	@JsonProperty("credit_id")
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	@JsonProperty("department")
	public String getDepartment() {
		return department;
	}

	@JsonProperty("department")
	public void setDepartment(String department) {
		this.department = department;
	}

	@JsonProperty("gender")
	public Integer getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("job")
	public String getJob() {
		return job;
	}

	@JsonProperty("job")
	public void setJob(String job) {
		this.job = job;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("profile_path")
	public String getProfilePath() {
		return profilePath;
	}

	@JsonProperty("profile_path")
	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	}