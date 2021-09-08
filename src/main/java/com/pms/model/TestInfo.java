package com.pms.model;

import java.math.BigDecimal;
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
@Table(name = "dms_test_infos")
public class TestInfo {

		@Id
		@Column(name = "id")
		@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private UUID id;

		@Column(name = "test_code")
		private String testCode;

		@Column(name = "test_name")
		private String testName;

		@Column(name = "test_rate",precision=10, scale=2,nullable = true)
		private BigDecimal testRate;
		
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

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getTestCode() {
			return testCode;
		}

		public void setTestCode(String testCode) {
			this.testCode = testCode;
		}

		

		public String getTestName() {
			return testName;
		}

		public void setTestName(String testName) {
			this.testName = testName;
		}

		public BigDecimal getTestRate() {
			return testRate;
		}

		public void setTestRate(BigDecimal testRate) {
			this.testRate = testRate;
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
         
		
	}