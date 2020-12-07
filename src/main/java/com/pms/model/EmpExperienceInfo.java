package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_emp_experience_infos")
public class EmpExperienceInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "employee_id")
	private UUID employeeId;

	@Column(name = "duration")
	private String experienceDuration;
	
	@Column(name = "designation")
	private String experienceDesign;
	
	@Column(name = "description")
	private String experienceDescrip;
	
	@Column(name = "company_name")
	private String companyName;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getExperienceDuration() {
		return experienceDuration;
	}

	public void setExperienceDuration(String experienceDuration) {
		this.experienceDuration = experienceDuration;
	}

	public String getExperienceDesign() {
		return experienceDesign;
	}

	public void setExperienceDesign(String experienceDesign) {
		this.experienceDesign = experienceDesign;
	}

	public String getExperienceDescrip() {
		return experienceDescrip;
	}

	public void setExperienceDescrip(String experienceDescrip) {
		this.experienceDescrip = experienceDescrip;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
