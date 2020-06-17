package com.starry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.bean.UserBean;
import com.bean.ScheBean;
import com.bean.HistoryBean;
import com.bean.TypeBean;
import utils.DBUtil;
/**
 * 关于图书连接数据库的所有操作的类
 */
public class BookDao {

	/**
	 * 添加图书信息，传入所有的信息
	 * @param card
	 * @param name
	 * @param type
	 * @param autho
	 * @param press
	 * @param num
	 */
	public void addBook(String card, String name, String type, String autho, String press, int num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert  into book(card,name,type,autho,press,num) values(?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, card);
			stm.setString(2, name);
			stm.setString(3, type);
			stm.setString(4, autho);
			stm.setString(5, press);
			stm.setInt(6, num);
			rs = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有的图书信息，返回的是ArrayList数组形式
	 * @return
	 */
	public ArrayList<ScheBean> get_ListInfo(){
		ArrayList<ScheBean> tag_Array = new ArrayList<ScheBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from sche";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ResultSet res=null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				ScheBean tag = new ScheBean();
				tag.setsNumber(rs.getString("sNumber"));
				tag.setdNumber(rs.getString("dNumber"));
				tag.setcNumber(rs.getString("cNumber"));
				tag.setTotal(rs.getInt("total"));
				tag.setPrice(rs.getString("price"));
				tag.setsTime(rs.getString("sTime"));
				tag.seteTime(rs.getString("eTime"));
				tag_Array.add(tag);
			}
			for(ScheBean sb:tag_Array) {
				String sl="select *from doctor where dNumber='"+sb.getdNumber()+"'";
				System.out.println(sl);
				stm=conn.prepareStatement(sl);
				res=stm.executeQuery();
				res.next();
				sb.setDname(res.getString(2));
			}
			for(ScheBean sb:tag_Array) {
				String sl="select *from department where cNumber='"+sb.getcNumber()+"'";
				stm=conn.prepareStatement(sl);
				res=stm.executeQuery();
				res.next();
				sb.setPartName(res.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	
	public ScheBean get_Info(int id){
		ScheBean tag = new ScheBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from sche where sNumber='"+id+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				tag.setsNumber(rs.getString("sNumber"));
				tag.setdNumber(rs.getString("dNumber"));
				tag.setcNumber(rs.getString("cNumber"));
				tag.setTotal(rs.getInt("total"));
				tag.setPrice(rs.getString("price"));
				tag.setsTime(rs.getString("sTime"));
				tag.seteTime(rs.getString("eTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag;
	}
	/**
	 * 获取借阅记录的全部信息，传入的条件有status，aid，表示搜索正在借阅的，或者已经还书的信息，aid代表当前登录用户
	 * @param
	 * @return
	 */
	public ArrayList<HistoryBean> get_HistoryListInfo(int aid){
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from uorder where id='"+aid+"'"+"order by oNumber desc ";
		System.out.println(sql);
		PreparedStatement stm = null;
		ResultSet rs = null;
		ResultSet res = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			/*System.out.println(rs.next());*/
			while(rs.next()){
				HistoryBean tag = new HistoryBean();
				tag.setsNumber(rs.getString("sNumber"));
				tag.setoNumber(rs.getString("oNumber"));
				tag.setPrice(rs.getString("price"));
				tag.setoTime(rs.getString("oTime"));
				tag.setId(rs.getString("id"));
				tag.setDocid(rs.getInt("docid"));
				tag.setStatus(rs.getInt("status"));
				tag_Array.add(tag);
			}
			for(HistoryBean sb:tag_Array) {
				String sl="select *from doctor where dNumber='"+sb.getDocid()+"'";
				System.out.println(sl);
				stm=conn.prepareStatement(sl);
				res=stm.executeQuery();
				res.next();
				sb.setDname(res.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		System.out.println(tag_Array);
		return tag_Array;
	}
	/**
	 * 获取借阅记录的全部信息，传入的条件有status，表示搜索正在借阅的，或者已经还书的信息
	 * @param status
	 * @return
	 */
	public ArrayList<HistoryBean> get_HistoryListInfo2(int status){
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from history where status='"+status+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				HistoryBean tag = new HistoryBean();
				tag.setsNumber(rs.getString("sNumber"));
			/*	tag.setdNumber(rs.getString("dNumber"));
				tag.setcNumber(rs.getString("cNumber"));
				tag.setTotal(rs.getInt("total"));
				tag.setPrice(rs.getString("price"));
				tag.setsTime(rs.getString("sTime"));
				tag.seteTime(rs.getString("eTime"));*/
				tag_Array.add(tag);
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
	 * 获取单个图书的信息，根据传入的bid来查找，返回一个BookBean数据类型
	 * @param bid
	 * @return
	 */
	public ScheBean get_BookInfo(int bid){
		ScheBean tag = new ScheBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from sche where sNumber='"+bid+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				tag.setsNumber(rs.getString("sNumber"));
				tag.setdNumber(rs.getString("dNumber"));
				tag.setcNumber(rs.getString("cNumber"));
				tag.setTotal(rs.getInt("total"));
				tag.setPrice(rs.getString("price"));
				tag.setsTime(rs.getString("sTime"));
				tag.seteTime(rs.getString("eTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag;
	}
	/**
	 * 修改图书的信息，bid作为条件，
	 */
	public void updateBook(int bid, String card, String name, String type, String autho, String press, int num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update book set name=?,card=?,type=?,autho=?,press=?,num=? where bid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, card);
			stm.setString(3, type);
			stm.setString(4, autho);
			stm.setString(5, press);
			stm.setInt(6, num);
			stm.setInt(7, bid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除图书信息，根据传入的bid作为条件
	 * @param
	 */
	public void deleteBook(int oid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from uorder where oNumber=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, oid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(uid);
		
	}
	/**
	 * 用户查找图书，根据输入的名称，使用like进行模糊查询，然后返回一个ArrayList数组类型
	 * @param name
	 * @return
	 */
	public ArrayList<ScheBean> getLikeList(String name) {
		// TODO Auto-generated method stub
		ArrayList<ScheBean> tag_Array = new ArrayList<ScheBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from sche where dNumber IN(select dNumber from doctor where name like '%"+name+"%')";
		System.out.println(sql);
		PreparedStatement stm = null;
		ResultSet rs = null;
		ResultSet res = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				ScheBean tag = new ScheBean();
				tag.setsNumber(rs.getString("sNumber"));
				System.out.println(rs.getString("sNumber"));
				tag.setdNumber(rs.getString("dNumber"));
				tag.setcNumber(rs.getString("cNumber"));
				tag.setTotal(rs.getInt("total"));
				tag.setPrice(rs.getString("price"));
				tag.setsTime(rs.getString("sTime"));
				tag.seteTime(rs.getString("eTime"));
				tag_Array.add(tag);
			}
			for(ScheBean sb:tag_Array) {
				String sl="select *from doctor where dNumber='"+sb.getdNumber()+"'";
				stm=conn.prepareStatement(sl);
				res=stm.executeQuery();
				res.next();
				System.out.println(res.getString(2));
				sb.setDname(res.getString(2));
			}
			for(ScheBean sb:tag_Array) {
				String sl="select *from department where cNumber='"+sb.getcNumber()+"'";
				stm=conn.prepareStatement(sl);
				res=stm.executeQuery();
				res.next();
				sb.setPartName(res.getString(2));
				System.out.println(res.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		System.out.println(tag_Array.isEmpty());
		System.out.println(tag_Array.isEmpty());
		System.out.println(tag_Array.isEmpty());
		return tag_Array;
	}
	/**
	 * 图书借阅函数，根据传入bid图书id，adminbean当前登录用户的信息，在借阅记录数据表中新插入一条记录
	 * @param bid
	 * @param adminbean
	 */
	public void borrowBook(int bid, UserBean adminbean) {
		// TODO Auto-generated method stub
		ScheBean bookbean = new ScheBean();
		bookbean = this.get_BookInfo(bid);
		//生成日期的功能
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int day = c.get(Calendar.DATE);  
		//生成借阅开始日期
		month = month + 1;
		String begintime = ""+year+"-"+month+"-"+day;
		//生成截止还书日期
		String endtime = ""+year+"-"+month+"-"+day;
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert  into uorder(sNumber,id,oTime,price,status) values(?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, bid);
			stm.setInt(2, adminbean.getId());
			stm.setString(3, begintime);
			stm.setString(4,bookbean.getPrice());
			stm.setInt(5, 1);
			rs = stm.executeUpdate();
			sql="update sche set total=total-1 where sNumber='"+bid+"'";
			stm.executeUpdate(sql);
			System.out.println("-----------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 还书功能的函数，根据传入的hid借阅记录id，讲status字段的值改为0，并将还书日期改变为当前日期
	 * @param hid
	 */
	public void borrowBook2(int hid) {
		// TODO Auto-generated method stub
		//生成日期
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int day = c.get(Calendar.DATE); 
		//生成还书日期
		String endtime = ""+year+"-"+month+"-"+day;
		Connection conn = DBUtil.getConnectDb();
		String sql = "update history set endtime=?,status=? where hid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, endtime);
			stm.setInt(2, 0);
			stm.setInt(3, hid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
