package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_mc_weighting_mst_infos")
public class LogMcWeightingMstInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "preparation_id")
	private UUID preparationId;
	
	@Column(name = "balacne_id")
	private UUID balanceId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getPreparationId() {
		return preparationId;
	}

	public void setPreparationId(UUID preparationId) {
		this.preparationId = preparationId;
	}

	public UUID getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(UUID balacneId) {
		this.balanceId = balacneId;
	}
	
	
	
}