package com.pms.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Table(name = "pms_sales_mst_infos")
public class SalesInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "sales_no")
	private String salesNo;
	
	@Column(name = "salesman_id")
	private UUID salesmanId;

	@Column(name = "sales_type_id")
	private String salesTypeId;
	
	@Column(name = "sales_date")
	private Date salesDate;
	
	@Column(name = "customer_id")
	private UUID customerId; 
	
	@Column(name = "sub_total",precision=10, scale=2)
	private BigDecimal  subTotal;
	
	@Column(name = "vat_pct",precision=10, scale=2)
	private BigDecimal  vatPct;
	
	@Column(name = "vat_total",precision=10, scale=2)
	private BigDecimal  vatTotal;
	
	@Column(name = "discount_pct",precision=10, scale=2)
	private BigDecimal  discountPct;
	
	@Column(name = "discount_total",precision=10, scale=2)
	private BigDecimal  discountTotal;
	
	@Column(name = "other_cost",precision=10, scale=2)
	private BigDecimal  otherCost;
	
	@Column(name = "grandTotal",precision=10, scale=2)
	private BigDecimal  grand_total;
	
	@Column(name = "paid_amount",precision=10, scale=2)
	private BigDecimal  paidAmount;
	
	@Column(name = "due_amount",precision=10, scale=2)
	private BigDecimal  dueAmount;
	
	@Column(name = "remarks")
	private String remarks;
	
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}

	public UUID getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(UUID salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getSalesTypeId() {
		return salesTypeId;
	}

	public void setSalesTypeId(String salesTypeId) {
		this.salesTypeId = salesTypeId;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getVatPct() {
		return vatPct;
	}

	public void setVatPct(BigDecimal vatPct) {
		this.vatPct = vatPct;
	}

	public BigDecimal getVatTotal() {
		return vatTotal;
	}

	public void setVatTotal(BigDecimal vatTotal) {
		this.vatTotal = vatTotal;
	}

	public BigDecimal getDiscountPct() {
		return discountPct;
	}

	public void setDiscountPct(BigDecimal discountPct) {
		this.discountPct = discountPct;
	}

	public BigDecimal getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(BigDecimal discountTotal) {
		this.discountTotal = discountTotal;
	}

	public BigDecimal getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	public BigDecimal getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(BigDecimal grand_total) {
		this.grand_total = grand_total;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	
	
}