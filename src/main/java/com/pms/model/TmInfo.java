package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_tm_verify_infos")
public class TmInfo extends CommonInfo{
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "sample_id")
	private UUID sampleId;
	
	@Column(name = "sample_rcv_id")
	private UUID sampleRcvId;
	
	@Column(name = "remarks")
	private String sampleDesc;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getSampleId() {
		return sampleId;
	}

	public void setSampleId(UUID sampleId) {
		this.sampleId = sampleId;
	}

	public UUID getSampleRcvId() {
		return sampleRcvId;
	}

	public void setSampleRcvId(UUID sampleRcvId) {
		this.sampleRcvId = sampleRcvId;
	}

	public String getSampleDesc() {
		return sampleDesc;
	}

	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}
	

	
	
	
	

	

	
	
}
