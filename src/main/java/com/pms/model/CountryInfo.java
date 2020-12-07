package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_country_infos")
public class CountryInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "ud_country_code")
	private String udCountryCode;

	@Column(name = "country_name")
	private String countryName;

	@Column(name = "main_curruncy")
	private String mainCurruncy;

	@Column(name = "main_langiage")
	private String mainLangiage;

	@Column(name = "total_pepole")
	private String totalPepole;

	@Column(name = "govt_code")
	private String govtCode;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;

	@Column(name = "branch_no")
	private String branchNo;

	@Column(name = "unit_no")
	private String unitNo;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUdCountryCode() {
		return udCountryCode;
	}

	public void setUdCountryCode(String udCountryCode) {
		this.udCountryCode = udCountryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getMainCurruncy() {
		return mainCurruncy;
	}

	public void setMainCurruncy(String mainCurruncy) {
		this.mainCurruncy = mainCurruncy;
	}

	public String getMainLangiage() {
		return mainLangiage;
	}

	public void setMainLangiage(String mainLangiage) {
		this.mainLangiage = mainLangiage;
	}

	public String getTotalPepole() {
		return totalPepole;
	}

	public void setTotalPepole(String totalPepole) {
		this.totalPepole = totalPepole;
	}

	public String getGovtCode() {
		return govtCode;
	}

	public void setGovtCode(String govtCode) {
		this.govtCode = govtCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

}
