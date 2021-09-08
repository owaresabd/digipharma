package com.pms.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Table(name = "pms_employee_infos")
public class EmployeeInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "ud_emp_no")
	private String udEmpNo;

	@Column(name = "emp_name")
	private String empName;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="desig_id", nullable=false)
	private DesignationInfo designationInfo;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "mother_name")
	private String motherName;

	@Type(type = "date")
	@Column(name = "dob")
	private Date dob;

	@Column(name = "gender_id")
	private String genderId;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "address")
	private String address;

	@Column(name = "nid")
	private String nid;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "email")
	private String email;

	@Column(name = "qualification")
	private String qualification;

	@Type(type = "date")
	@Column(name = "join_date")
	private Date joiningDate;

	@Column(name = "status")
	private String status;
	
	@Column(name = "company_id",updatable=false)
	private UUID companyId;
	
	@Column(name = "created_by",updatable=false)
	@CreatedBy
	private UUID createdBy;
	
	@Column(name = "created_at", updatable=false)
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@Column(name = "updated_by",insertable=false)
	@CreatedBy
	private UUID updatedBy;
	
	@Column(name = "updated_at",insertable=false)
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUdEmpNo() {
		return udEmpNo;
	}

	public void setUdEmpNo(String udEmpNo) {
		this.udEmpNo = udEmpNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	

	public DesignationInfo getDesignationInfo() {
		return designationInfo;
	}

	public void setDesignationInfo(DesignationInfo designationInfo) {
		this.designationInfo = designationInfo;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getCompanyId() {
		return companyId;
	}

	public void setCompanyId(UUID companyId) {
		this.companyId = companyId;
	}

	public UUID getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public UUID getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UUID updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
