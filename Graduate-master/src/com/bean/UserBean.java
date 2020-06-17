package com.bean;

public class UserBean {
	private String uname;
	private String pwd;
	private Integer id;
	private String tel;
	private String info;
	private Integer integrity;

	public Integer getIntegrity() {
		return integrity;
	}

	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}
//	private String status;
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	public UserBean() {
	}

	public UserBean(String uname, String pwd, String tel, String info,Integer integrity) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.tel=tel;
		this.info=info;
		this.integrity=integrity;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUname() {
		return uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [uname=" + uname + ", pwd=" + pwd + ", id=" + id + ", tel="
				+ tel + ", info=" + info + "]";
	}

	public UserBean(String uname, String pwd, Integer id, String tel, String info) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.id = id;
		this.tel = tel;
		this.info = info;
	}
}
