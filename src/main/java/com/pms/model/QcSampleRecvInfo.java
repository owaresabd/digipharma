package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_qc_sample_rcv_infos")
public class QcSampleRecvInfo extends CommonInfo{

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "qc_req_id")
	private UUID qcReqId;
	
	@Column(name = "sample_desc")
	private String sampleDesc;
	
	@Column(name = "sample_id")
	private UUID sampleId;
	
	@Column(name = "st_chemi_qty")
	private String stChemiQty;
	
	@Column(name = "st_micro_qty")
	private String stMicroQty;
	
	@Column(name = "st_qty")
	private String stQty;
	
	@Column(name = "st_unit_id")
	private UUID stUnitId;
	
	@Column(name = "st_equ_id")
	private UUID stEquId;
	
	@Column(name = "st_room_id")
	private UUID stRoomId;
	
	@Column(name = "st_rack_id")
	private UUID stRackId;
	
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

	public String getSampleDesc() {
		return sampleDesc;
	}

	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}

	public String getStChemiQty() {
		return stChemiQty;
	}

	public void setStChemiQty(String stChemiQty) {
		this.stChemiQty = stChemiQty;
	}

	public String getStMicroQty() {
		return stMicroQty;
	}

	public void setStMicroQty(String stMicroQty) {
		this.stMicroQty = stMicroQty;
	}

	public String getStQty() {
		return stQty;
	}

	public void setStQty(String stQty) {
		this.stQty = stQty;
	}

	public UUID getStUnitId() {
		return stUnitId;
	}

	public void setStUnitId(UUID stUnitId) {
		this.stUnitId = stUnitId;
	}

	public UUID getStEquId() {
		return stEquId;
	}

	public void setStEquId(UUID stEquId) {
		this.stEquId = stEquId;
	}

	public UUID getStRoomId() {
		return stRoomId;
	}

	public void setStRoomId(UUID stRoomId) {
		this.stRoomId = stRoomId;
	}

	public UUID getStRackId() {
		return stRackId;
	}

	public void setStRackId(UUID stRackId) {
		this.stRackId = stRackId;
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

	public UUID getQcReqId() {
		return qcReqId;
	}

	public void setQcReqId(UUID qcReqId) {
		this.qcReqId = qcReqId;
	}

	public UUID getSampleId() {
		return sampleId;
	}

	public void setSampleId(UUID sampleId) {
		this.sampleId = sampleId;
	}
	
}
