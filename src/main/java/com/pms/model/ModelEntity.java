package com.pms.model;

import java.util.Date;

public class ModelEntity {

	private String itemName;
	private String udItemNo;
	private String uomName;
	private String groupName;
	private String deptName;
	private int opbalStock;
	private int receiveStock;
	private int issueStock;
	private int stockQty;
	private Date tranDate;
	private String tranDesc;
	private int prQty;
	private int clQty;
	private String ranks;
	private double price;
	private double total;
	private double disc;
	private double vat;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUdItemNo() {
		return udItemNo;
	}

	public void setUdItemNo(String udItemNo) {
		this.udItemNo = udItemNo;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getOpbalStock() {
		return opbalStock;
	}

	public void setOpbalStock(int opbalStock) {
		this.opbalStock = opbalStock;
	}

	public int getReceiveStock() {
		return receiveStock;
	}

	public void setReceiveStock(int receiveStock) {
		this.receiveStock = receiveStock;
	}

	public int getIssueStock() {
		return issueStock;
	}

	public void setIssueStock(int issueStock) {
		this.issueStock = issueStock;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranDesc() {
		return tranDesc;
	}

	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public int getPrQty() {
		return prQty;
	}

	public void setPrQty(int prQty) {
		this.prQty = prQty;
	}

	public int getClQty() {
		return clQty;
	}

	public void setClQty(int clQty) {
		this.clQty = clQty;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRanks() {
		return ranks;
	}

	public void setRanks(String ranks) {
		this.ranks = ranks;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDisc() {
		return disc;
	}

	public void setDisc(double disc) {
		this.disc = disc;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

}
