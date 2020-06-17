package com.starry.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
/**
 * @author Administrator
 *
 */
public class Order {
	private Integer oNumber;
	private Integer sNumber;
	private Integer id;
	private String oTime;
	private Integer price;
	private Integer status;
	private String info;
	private Integer pid;
	//飞数据库字段

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getDocid() {
		return docid;
	}

	public void setDocid(Integer docid) {
		this.docid = docid;
	}

	private Integer docid;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getoNumber() {
		return oNumber;
	}
	public void setoNumber(Integer oNumber) {
		this.oNumber = oNumber;
	}
	public Integer getsNumber() {
		return sNumber;
	}
	public void setsNumber(Integer sNumber) {
		this.sNumber = sNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getoTime() {
		return oTime;
	}
	public void setoTime(String oTime) {
		this.oTime = oTime;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Order [oNumber=" + oNumber + ", sNumber=" + sNumber
				+ ", id=" + id + ", oTime=" + oTime + ", price=" + price
				+ ", info=" + info +"]";
	}
	public Order(Integer sNumber, Integer id, String oTime,
				 Integer price,Integer status,String info) {
		super();
//		this.oNumber = oNumber;
		this.sNumber = sNumber;
		this.id = id;
		this.oTime = oTime;
		this.price = price;
		this.status = 1;
		this.info=info;
	}

	public Order(Integer oNumber,Integer sNumber, Integer id, String oTime,
				 Integer price,Integer status,String info,Integer docid,Integer pid) {
		super();
		this.oNumber = oNumber;
		this.sNumber = sNumber;
		this.id = id;
		this.oTime = oTime;
		this.price = price;
		this.status = 1;
		this.info=info;
		this.docid=docid;
		this.pid=pid;
	}
	
}
