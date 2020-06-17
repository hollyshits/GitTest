package com.starry.entity;

import javax.persistence.Entity;

@Entity
/**
 * @author Administrator
 *
 */
public class Department {
	private Integer cNumber;
	private String dName;
	private String dDec;//

	public Integer getDocnum() {
		return docnum;
	}

	public void setDocnum(Integer docnum) {
		this.docnum = docnum;
	}

	private Integer docnum;
	
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
	public String getdDec() {
		return dDec;
	}
	public void setdDec(String dDec) {
		this.dDec = dDec;
	}
	@Override
	public String toString() {
		return "Department [cNumber=" + cNumber + ", dName=" + dName
				+ ", dDec=" + dDec + "]";
	}
	public Department(Integer cNumber, String dName, String dDec) {
		super();
		this.cNumber = cNumber;
		this.dName = dName;
		this.dDec = dDec;
	}
	public Department(Integer cNumber, String dName, String dDec,Integer docnum) {
		super();
		this.cNumber = cNumber;
		this.dName = dName;
		this.dDec = dDec;
		this.docnum=docnum;
	}
	
	
}
