package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_emp_technical_infos")
public class EmpTechnicalInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "employee_id")
	private UUID employeeId;
	
	@Column(name = "title")
	private String tecTitle;
	
	@Column(name = "description")
	private String tecDescrip;
	
	
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

	public String getTecTitle() {
		return tecTitle;
	}

	public void setTecTitle(String tecTitle) {
		this.tecTitle = tecTitle;
	}

	public String getTecDescrip() {
		return tecDescrip;
	}

	public void setTecDescrip(String tecDescrip) {
		this.tecDescrip = tecDescrip;
	}
	
	
}
