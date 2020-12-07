package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_ret_sample_rcv_infos")
public class RetentionSampleRecvInfo extends CommonInfo{

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "ret_req_id")
	private UUID retReqId;
	
	@Column(name = "sample_desc")
	private String sampleDesc;
	
	@Column(name = "sample_id")
	private UUID sampleId;
	
	@Column(name = "ret_qty")
	private String retQty;
	
	@Column(name = "ret_unit_id")
	private UUID retUnitId;
	
	@Column(name = "ret_equ_id")
	private UUID retEquId;
	
	@Column(name = "ret_room_id")
	private UUID retRoomId;
	
	@Column(name = "ret_rack_id")
	private UUID retRackId;
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRetReqId() {
		return retReqId;
	}

	public void setRetReqId(UUID retReqId) {
		this.retReqId = retReqId;
	}

	public String getSampleDesc() {
		return sampleDesc;
	}

	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}

	public String getRetQty() {
		return retQty;
	}

	public void setRetQty(String retQty) {
		this.retQty = retQty;
	}

	public UUID getRetUnitId() {
		return retUnitId;
	}

	public void setRetUnitId(UUID retUnitId) {
		this.retUnitId = retUnitId;
	}

	public UUID getRetEquId() {
		return retEquId;
	}

	public void setRetEquId(UUID retEquId) {
		this.retEquId = retEquId;
	}

	public UUID getRetRoomId() {
		return retRoomId;
	}

	public void setRetRoomId(UUID retRoomId) {
		this.retRoomId = retRoomId;
	}

	public UUID getRetRackId() {
		return retRackId;
	}

	public void setRetRackId(UUID retRackId) {
		this.retRackId = retRackId;
	}

	public UUID getSampleId() {
		return sampleId;
	}

	public void setSampleId(UUID sampleId) {
		this.sampleId = sampleId;
	}
	
}
