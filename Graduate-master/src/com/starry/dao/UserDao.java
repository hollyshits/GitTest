package com.starry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.UserBean;
import com.bean.ScheBean;
import utils.DBUtil;
/**
 * 有关读者账号的连接数据库操作，登录验证，注册，修改账号，修改密码
 */
public class UserDao {
	
	/**
	 * 登录验证功能，传入用户名和密码，在数据库中查找，如果找到了，返回true，没找到则返回false
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean Login_verify(String username,String password){
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from user where uname='"+username+"' and pwd='"+password+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return false;
	}
	/**
	 * 注册账号的函数，传入账号，密码，姓名，邮箱，手机号，借阅天数，可借阅数
	 * @param username
	 * @param password
	 * @param
	 * @param phone
	 */
	public void Register(String username, String password, String info, String phone) {
		// TODO Auto-generated method stub
				Connection conn = DBUtil.getConnectDb();
				String sql = "insert  into user(uname,pwd,tel,info) values(?,?,?,?)";
				int rs = 0;
				PreparedStatement stm = null;
				try {
					stm = conn.prepareStatement(sql);
					stm.setString(1, username);
					stm.setString(2, password);
					stm.setString(3, phone);
					stm.setString(4, info);
					rs = stm.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	/**
	 * 根据传入的账号，密码，来查找对应的读者信息，返回一个AdminBean类型，
	 * @param username
	 * @param password
	 * @return
	 */
	public UserBean getAdminInfo(String username, String password) {
		// TODO Auto-generated method stub
		UserBean adminbean = new UserBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from user where uname='"+username+"' and pwd='"+password+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
//			stm.setString(1,name);
			rs = stm.executeQuery();
			if(rs.next()){
				adminbean.setId(rs.getInt("id"));
				adminbean.setUname(rs.getString("uname"));
				adminbean.setTel(rs.getString("tel"));
				adminbean.setInfo(rs.getString("info"));
				adminbean.setIntegrity(rs.getInt("integrity"));
				System.out.println(adminbean.getIntegrity());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}
	/**
	 * 获取全部用户的信息，其中sql语句中的status=1，表示只查找读者，不显示管理员的
	 * @return
	 */
	public ArrayList<UserBean> get_ListInfo(){
		ArrayList<UserBean> tag_Array = new ArrayList<UserBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				UserBean adminbean = new UserBean();
				adminbean.setId(rs.getInt("aid"));
				adminbean.setUname(rs.getString("username"));
				adminbean.setPwd(rs.getString("password"));
				adminbean.setTel(rs.getString("phone"));
				adminbean.setInfo(rs.getString("status"));
				tag_Array.add(adminbean);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据
	 * @param aid
	 * @return
	 */
	public UserBean get_AidInfo(int aid){
		UserBean adminbean = new UserBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid="+aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if(rs.next()){
				adminbean.setId(rs.getInt("aid"));
				adminbean.setUname(rs.getString("username"));
				adminbean.setPwd(rs.getString("password"));
				adminbean.setTel(rs.getString("phone"));
				adminbean.setInfo(rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}
	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据，与上一个相似，只是aid的类型为String，
	 * @param aid
	 * @return
	 */
	public UserBean get_AidInfo2(String aid){
		UserBean adminbean = new UserBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from user where uname='"+aid+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = stm.executeQuery();
			if(rs.next()){
				adminbean.setId(rs.getInt("id"));
				adminbean.setUname(rs.getString("uname"));
				adminbean.setPwd(rs.getString("pwd"));
				adminbean.setTel(rs.getString("tel"));
				adminbean.setInfo(rs.getString("info"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}
	/**
	 * 修改读者的信息，
	 */
	public void updateUser(int aid, String username, String password, String phone,
			String info) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update user set uname=?,pwd=?,tel=?,info=? where id=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, password);
			stm.setString(3, phone);
			stm.setString(4, info);
			stm.setInt(5, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除用户的信息，根据传入的aid作为条件
	 * @param aid
	 */
	public void deleteUser(int aid) {
		// TODO Auto-generated method stub
				Connection conn = DBUtil.getConnectDb();
				String sql = "delete from admin where aid=?";
				PreparedStatement stm = null;
				try {
					stm = conn.prepareStatement(sql);
					stm.setInt(1, aid);
					stm.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}
}
