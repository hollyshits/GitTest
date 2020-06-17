package com.bean;
public class HistoryBean {
	private String oNumber;
	private String  sNumber;
	private String  id;
	private String oTime;
	private  String price;
	private String dname;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private Integer status;

	public Integer getDocid() {
		return docid;
	}

	public void setDocid(Integer docid) {
		this.docid = docid;
	}

	private Integer docid;
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getoNumber() {
		return oNumber;
	}
	public void setoNumber(String oNumber) {
		this.oNumber = oNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getoTime() {
		return oTime;
	}
	public void setoTime(String oTime) {
		this.oTime = oTime;
	}
	public HistoryBean() {
		
	}
	public String getsNumber() {
		return sNumber;
	}
	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public HistoryBean(String oNumber, String sNumber, String id, String oTime,
			String price,Integer docid,Integer status) {
		super();
		this.oNumber = oNumber;
		this.sNumber = sNumber;
		this.id = id;
		this.price = price;
		this.oTime = oTime;
		this.docid=docid;
		this.status=status;
	}
	@Override
	public String toString() {
		return "Sch [sNumber=" + sNumber + ", oNumber=" + oNumber
				+ ", price="
				+ price + ", oTime=" + oTime + ", id=" + id + "]";
	}
	
	
}
