package com.pms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Table(name = "dms_doctor_infos")
public class DoctorInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "doctor_name")
	private String doctorName;
	
	@Column(name = "qualification")
	private String qualification;
	
	@Column(name = "bmdc_no")
	private String bmdcNo;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="designation_id", nullable=false)
	private DesignationInfo designationInfo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id", nullable=false)
	private DepartmentInfo departmentInfo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="speciality_id", nullable=false)
	private SpecialityInfo specialityInfo;


	@Column(name = "primary_consult_fee",precision=10, scale=2,nullable = true)
	private BigDecimal primaryConsultFee;
	
	@Column(name = "followup_consult_fee",precision=10, scale=2,nullable = true)
	private BigDecimal followupConsultFee;
	
	

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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getBmdcNo() {
		return bmdcNo;
	}

	public void setBmdcNo(String bmdcNo) {
		this.bmdcNo = bmdcNo;
	}

	public DesignationInfo getDesignationInfo() {
		return designationInfo;
	}

	public void setDesignationInfo(DesignationInfo designationInfo) {
		this.designationInfo = designationInfo;
	}

	public DepartmentInfo getDepartmentInfo() {
		return departmentInfo;
	}

	public void setDepartmentInfo(DepartmentInfo departmentInfo) {
		this.departmentInfo = departmentInfo;
	}

	public SpecialityInfo getSpecialityInfo() {
		return specialityInfo;
	}

	public void setSpecialityInfo(SpecialityInfo specialityInfo) {
		this.specialityInfo = specialityInfo;
	}

	

	public BigDecimal getPrimaryConsultFee() {
		return primaryConsultFee;
	}

	public void setPrimaryConsultFee(BigDecimal primaryConsultFee) {
		this.primaryConsultFee = primaryConsultFee;
	}

	public BigDecimal getFollowupConsultFee() {
		return followupConsultFee;
	}

	public void setFollowupConsultFee(BigDecimal followupConsultFee) {
		this.followupConsultFee = followupConsultFee;
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
