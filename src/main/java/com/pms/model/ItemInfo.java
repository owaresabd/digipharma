package com.pms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Table(name = "pms_item_infos")
public class ItemInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "item_no",updatable = false)
	private String itemNo;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "generic_id")
	private UUID genericId;
	
	@Column(name = "type_id")
	private UUID typeId;
	
	@Column(name = "manufacturer_id")
	private UUID manufacturerId;
	
	@Column(name = "country_id")
	private UUID countryId;
	
	@Column(name = "unit_id")
	private UUID unitId;
	
	@Column(name = "purchase_price",precision=10, scale=2,nullable = true)
	private BigDecimal purchasePrice;
	
	@Column(name = "sales_price",precision=10, scale=2,nullable = true)
	private BigDecimal salesPrice;
	
	@Column(name = "rack_no")
	private String rackNo;
	
	@Column(name = "row_no")
	private String rowNo;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "status")
	private String status;

	@Column(name = "company_id",updatable=false)
	private UUID companyId;
	
	@Column(name = "created_by",updatable=false)
	@CreatedBy
	private UUID createdBy;
	
	@Column(name = "created_at", updatable=false)
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@Column(name = "updated_by",insertable=false)
	@CreatedBy
	private UUID updatedBy;
	
	@Column(name = "updated_at",insertable=false)
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "generic_id", referencedColumnName = "id",insertable = false, updatable = false)
    private GenericInfo genericInfo;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ItemTypeInfo itemTypeInfo;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ManufacturerInfo manufacturerInfo;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "unit_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UnitInfo unitInfo;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "country_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CountryInfo countryInfo;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public UUID getGenericId() {
		return genericId;
	}

	public void setGenericId(UUID genericId) {
		this.genericId = genericId;
	}

	public UUID getTypeId() {
		return typeId;
	}

	public void setTypeId(UUID typeId) {
		this.typeId = typeId;
	}

	

	public CountryInfo getCountryInfo() {
		return countryInfo;
	}

	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	public UUID getCountryId() {
		return countryId;
	}

	public void setCountryId(UUID countryId) {
		this.countryId = countryId;
	}

	public UUID getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(UUID manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public UUID getUnitId() {
		return unitId;
	}

	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}

	

	

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getRackNo() {
		return rackNo;
	}

	public void setRackNo(String rackNo) {
		this.rackNo = rackNo;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getCompanyId() {
		return companyId;
	}

	public void setCompanyId(UUID companyId) {
		this.companyId = companyId;
	}

	public UUID getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public UUID getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UUID updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public GenericInfo getGenericInfo() {
		return genericInfo;
	}

	public void setGenericInfo(GenericInfo genericInfo) {
		this.genericInfo = genericInfo;
	}

	public ItemTypeInfo getItemTypeInfo() {
		return itemTypeInfo;
	}

	public void setItemTypeInfo(ItemTypeInfo itemTypeInfo) {
		this.itemTypeInfo = itemTypeInfo;
	}

	public ManufacturerInfo getManufacturerInfo() {
		return manufacturerInfo;
	}

	public void setManufacturerInfo(ManufacturerInfo manufacturerInfo) {
		this.manufacturerInfo = manufacturerInfo;
	}

	public UnitInfo getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}
	
	

}