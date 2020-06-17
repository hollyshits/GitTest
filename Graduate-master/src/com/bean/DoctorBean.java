package com.bean;

public class DoctorBean {
	private String dnumber;
	private String name;
	private Integer dPwd;
	private String cnumber;
	private String dinfo;
	private String dresume;
	private String dtel;
	private String demail;
	public String getDnumber() {
		return dnumber;
	}
	public void setDnumber(String dnumber) {
		this.dnumber = dnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getdPwd() {
		return dPwd;
	}
	public void setdPwd(Integer dPwd) {
		this.dPwd = dPwd;
	}
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public String getDinfo() {
		return dinfo;
	}
	public void setDinfo(String dinfo) {
		this.dinfo = dinfo;
	}
	public String getDresume() {
		return dresume;
	}
	public void setDresume(String dresume) {
		this.dresume = dresume;
	}
	public String getDtel() {
		return dtel;
	}
	public void setDtel(String dtel) {
		this.dtel = dtel;
	}
	public String getDemail() {
		return demail;
	}
	public void setDemail(String demail) {
		this.demail = demail;
	}
	@Override
	public String toString() {
		return "DoctorBean [dnumber=" + dnumber + ", name=" + name + ", dPwd=" + dPwd + ", cnumber=" + cnumber
				+ ", dinfo=" + dinfo + ", dresume=" + dresume + ", dtel=" + dtel + ", demail=" + demail + "]";
	}
	
//	private String status;
	
}
