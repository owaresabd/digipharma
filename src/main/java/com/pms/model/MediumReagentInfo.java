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
@Table(name = "lims_medium_reagent_infos")
public class MediumReagentInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "code_no")
	private String codeNo;
	
	@Column(name = "reagent_name")
	private String reagentName;
	
	@Transient
	private String labBatchNo;
	@Transient
	private String batchSeqNo;
	
	@Column(name = "status")
	private String status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getReagentName() {
		return reagentName;
	}

	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}

	public String getLabBatchNo() {
		return labBatchNo;
	}

	public void setLabBatchNo(String labBatchNo) {
		this.labBatchNo = labBatchNo;
	}

	public String getBatchSeqNo() {
		return batchSeqNo;
	}

	public void setBatchSeqNo(String batchSeqNo) {
		this.batchSeqNo = batchSeqNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}