package com.starry.entity;


public class Sch {
	private Integer  sNumber;
	private Integer dNumber;
	private Integer  cNumber;


	private Integer total;
	private  Integer price;
	private String sTime;
	private String eTime;
	private int status;
	//非数据库字段
	private String dname;

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	private String percent;

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	private int rest;

	public void setStatus(int status) {
		this.status = 1;
	}
	public int getsNumber() {return sNumber; }
	public int getStatus() {return status; }
	public void setsNumber(int sNumber) {this.sNumber = sNumber;}
	public Integer getdNumber() {
		return dNumber;
	}
	public void setdNumber(Integer dNumber) {
		this.dNumber = dNumber;
	}
	public Integer getcNumber() {
		return cNumber;
	}
	public void setcNumber(Integer cNumber) {
		this.cNumber = cNumber;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public Sch(Integer sNumber,Integer dNumber, Integer cNumber, Integer total,
			   Integer price, String sTime, String eTime,Integer status,Integer rest,String dname) {
		super();
		this.sNumber = sNumber;
		this.dNumber = dNumber;
		this.cNumber = cNumber;
		this.total = total;
		this.price = price;
		this.sTime = sTime;
		this.eTime = eTime;
		this.status= status;
		this.rest=rest;
		this.dname=dname;
	}

	public Sch(Integer sNumber,Integer dNumber, Integer cNumber, Integer total,
			   Integer price, String sTime, String eTime,Integer status,Integer rest) {
		super();
		this.sNumber = sNumber;
		this.dNumber = dNumber;
		this.cNumber = cNumber;
		this.total = total;
		this.price = price;
		this.sTime = sTime;
		this.eTime = eTime;
		this.status= status;
		this.rest=rest;
	}
	@Override
	public String toString() {
		return "Sch [dNumber=" + dNumber
				+ ", cNumber=" + cNumber + ", total=" + total + ", price="
				+ price + ", sTime=" + sTime + ", eTime=" + eTime + "]";
	}
	
	
}
