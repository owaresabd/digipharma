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
@Table(name = "lims_dispose_product_infos")
public class DisposeProductInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "dispose_id")
	private UUID disposeId;
	
	@Column(name = "product_id")
	private UUID productId;
	
	@Transient
	private String productName;
	
	@Column(name = "product_code")
	private String productCode;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "unit_id")
	private UUID productUnitId;
	
	@Transient
	private String unitName;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getDisposeId() {
		return disposeId;
	}

	public void setDisposeId(UUID disposeId) {
		this.disposeId = disposeId;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public UUID getProductUnitId() {
		return productUnitId;
	}

	public void setProductUnitId(UUID productUnitId) {
		this.productUnitId = productUnitId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	
	
	
}
