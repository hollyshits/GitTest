package com.starry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.bean.DoctorBean;
import utils.DBUtil;

public class DoctorDao {
	
	public ResultSet getinfo(String sid) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "select *from doctor where dNumber='"+sid+"'";
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		ResultSet res=null;
		res=stm.executeQuery();
		return res;
	}
	public ResultSet getAll() throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "select *from doctor";
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		ResultSet res=null;
		res=stm.executeQuery();
		return res;
	}
}
