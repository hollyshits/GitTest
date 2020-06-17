package com.starry.entity;

public class DepartmentInfo {
	private Integer cNumber;
	private String dName;
	private Integer dNumber;
	public Integer getcNumber() {
		return cNumber;
	}
	public void setcNumber(Integer cNumber) {
		this.cNumber = cNumber;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public Integer getdNumber() {
		return dNumber;
	}
	public void setdNumber(Integer dNumber) {
		this.dNumber = dNumber;
	}
	public DepartmentInfo(Integer cNumber, String dName, Integer dNumber) {
		super();
		this.cNumber = cNumber;
		this.dName = dName;
		this.dNumber = dNumber;
	}
	@Override
	public String toString() {
		return "DepartmentInfo [cNumber=" + cNumber + ", dName=" + dName
				+ ", dNumber=" + dNumber + "]";
	}
	
	
	
}
