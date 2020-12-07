package com.pms.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "log_mc_lb_preparation_infos")
public class LogMCLbPreparationInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Transient
	private UUID disinfectantId;
		
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "operate_by")
	private UUID operateBy;

	@Transient
	private String operateByName;
	
	@Column(name = "checked_by")
	private UUID checkedBy;
	
	@Transient
	private String checkedStatus;
	
	@Transient
	private String checkedByName;
	
	@Column(name = "remarks")
	private String remarks;

	@Transient
	private String amountUsedWater;
	
	@Transient
	private String totalAmountPreparation;
	
	@Transient
	private String amountUsedDisinfectant;
	
	@Transient
	private String disinfectantName;
	
	@Type(type = "date")
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public UUID getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(UUID operateBy) {
		this.operateBy = operateBy;
	}

	
	public String getOperateByName() {
		return operateByName;
	}

	public void setOperateByName(String operateByName) {
		this.operateByName = operateByName;
	}

	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public UUID getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(UUID checkedBy) {
		this.checkedBy = checkedBy;
	}

	public String getCheckedByName() {
		return checkedByName;
	}

	public void setCheckedByName(String checkedByName) {
		this.checkedByName = checkedByName;
	}

	public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public UUID getDisinfectantId() {
		return disinfectantId;
	}

	public void setDisinfectantId(UUID disinfectantId) {
		this.disinfectantId = disinfectantId;
	}

	public String getAmountUsedWater() {
		return amountUsedWater;
	}

	public void setAmountUsedWater(String amountUsedWater) {
		this.amountUsedWater = amountUsedWater;
	}

	public String getTotalAmountPreparation() {
		return totalAmountPreparation;
	}

	public void setTotalAmountPreparation(String totalAmountPreparation) {
		this.totalAmountPreparation = totalAmountPreparation;
	}

	public String getAmountUsedDisinfectant() {
		return amountUsedDisinfectant;
	}

	public void setAmountUsedDisinfectant(String amountUsedDisinfectant) {
		this.amountUsedDisinfectant = amountUsedDisinfectant;
	}

	public String getDisinfectantName() {
		return disinfectantName;
	}

	public void setDisinfectantName(String disinfectantName) {
		this.disinfectantName = disinfectantName;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	

}
