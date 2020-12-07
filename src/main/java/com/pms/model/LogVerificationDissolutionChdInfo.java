package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lims_log_verification_dissolution_chd_infos")
public class LogVerificationDissolutionChdInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID chdId;	

	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private String vesselNo;
	
	@Transient
	private String tempAfterTest;
	
	@Transient
	private String tempBeforeTest;
	
	@Transient
	private String sampleInputTime;

	@Transient
	private String sampleWithdrawalTime;
	
	@Column(name = "master_id")
	private UUID masterId;	

	public String getVesselNo() {
		return vesselNo;
	}

	public void setVesselNo(String vesselNo) {
		this.vesselNo = vesselNo;
	}

	public UUID getChdId() {
		return chdId;
	}

	public void setChdId(UUID chdId) {
		this.chdId = chdId;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTempAfterTest() {
		return tempAfterTest;
	}

	public void setTempAfterTest(String tempAfterTest) {
		this.tempAfterTest = tempAfterTest;
	}

	public String getTempBeforeTest() {
		return tempBeforeTest;
	}

	public void setTempBeforeTest(String tempBeforeTest) {
		this.tempBeforeTest = tempBeforeTest;
	}

	public String getSampleInputTime() {
		return sampleInputTime;
	}

	public void setSampleInputTime(String sampleInputTime) {
		this.sampleInputTime = sampleInputTime;
	}

	public String getSampleWithdrawalTime() {
		return sampleWithdrawalTime;
	}

	public void setSampleWithdrawalTime(String sampleWithdrawalTime) {
		this.sampleWithdrawalTime = sampleWithdrawalTime;
	}

	public UUID getMasterId() {
		return masterId;
	}

	public void setMasterId(UUID masterId) {
		this.masterId = masterId;
	}

}
