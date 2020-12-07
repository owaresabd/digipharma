package com.pms.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "lims_emp_training_infos")
public class EmpTrainingInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "employee_id")
	private UUID employeeId;
	
	@Type(type = "date")
	@Column(name = "train_date")
	private Date trainDate;

	@Column(name = "course_title")
	private String courseTitle;
	
	@Column(name = "course_inst")
	private String courseInst;
	
	@Column(name = "assesment")
	private String assesment;
	

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

	public Date getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(Date trainDate) {
		this.trainDate = trainDate;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseInst() {
		return courseInst;
	}

	public void setCourseInst(String courseInst) {
		this.courseInst = courseInst;
	}

	public String getAssesment() {
		return assesment;
	}

	public void setAssesment(String assesment) {
		this.assesment = assesment;
	}
	

}
