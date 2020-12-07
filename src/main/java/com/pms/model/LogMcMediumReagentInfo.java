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
@Table(name = "log_mc_medium_reagent_infos")
public class LogMcMediumReagentInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "reagent_id")
	private UUID reagentId;
	
	@Transient
	private String reagentName;

	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "batch_seq_no")
	private Integer batchSeqNo;

	@Column(name = "batch_size")
	private String batchSize;

	@Type(type = "date")
	@Column(name = "prep_date")
	private Date prepDate;

	@Type(type = "date")
	@Column(name = "exp_date")
	private Date expDate;
	
	@Column(name = "is_weight")
	private String isWeight;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getReagentId() {
		return reagentId;
	}

	public void setReagentId(UUID reagentId) {
		this.reagentId = reagentId;
	}

	public String getReagentName() {
		return reagentName;
	}

	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getBatchSeqNo() {
		return batchSeqNo;
	}

	public void setBatchSeqNo(Integer batchSeqNo) {
		this.batchSeqNo = batchSeqNo;
	}

	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}

	public Date getPrepDate() {
		return prepDate;
	}

	public void setPrepDate(Date prepDate) {
		this.prepDate = prepDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getIsWeight() {
		return isWeight;
	}

	public void setIsWeight(String isWeight) {
		this.isWeight = isWeight;
	}
	

}