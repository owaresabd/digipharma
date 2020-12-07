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
@Table(name = "lims_product_infos")
public class ProductInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "product_id")
	private UUID productId;
	
	@Transient
	private String productName;
	
	@Column(name = "shelf_life")
	private String shelfLife;

	
	@Column(name = "batch_size")
	private String batchSize;
	
	@Column(name = "unit_id")
	private UUID unitId;
	
	@Transient
	private String unitName;
	
	@Column(name = "pack_size")
	private String packSize;

	
	@Column(name = "status")
	private String status;


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public UUID getProductId() {
		return productId;
	}


	public void setProductId(UUID productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getShelfLife() {
		return shelfLife;
	}


	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}


	public String getBatchSize() {
		return batchSize;
	}


	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}


	public UUID getUnitId() {
		return unitId;
	}


	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}


	public String getPackSize() {
		return packSize;
	}


	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	

	

	

}
