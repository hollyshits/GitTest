package com.starry.dao;

import java.util.List;

import com.starry.entity.*;
import org.apache.ibatis.annotations.Param;

public interface IUserDao {

	public abstract Order getOrderById(Integer id);

	public abstract void plusRest(Integer sNumber);

	public abstract void minusIntegrity(integrity inte);

	public abstract void minusRest(Integer schid);

	public abstract void changeOrderstatus(Integer oid,Integer status);

	public abstract void changePvstatus(Integer ppid,Integer status);

	public abstract Department getDepartbydoc(Integer id);

	public abstract List<Doctor> getRandomDocList();

	public abstract Doctor getdoc(Integer id);

	public abstract List<integrity> getUserInter(Integer id);

	public abstract List<Integer> gethisdoc2(Integer id);

	public abstract List<Integer> gethisdoc(Integer id);

	public abstract List<Order> selectodayorder(String date);

	public abstract List<User> selectAll();// 
	public User findUser(String usercode,String userpwd);

	public abstract int deleteById(int id);//

	public abstract User getuser(Integer id);

	public abstract int register(User users);
	public abstract int update(User user);//

	public abstract List<User> userLogin(User user);
	public abstract int updateUser(User user);
	//����û�ԤԼ
	public abstract int insertOrder(Order order);
	public abstract List<OrderInfo> getOrderInfoById(Integer id);

	public abstract int insertFeedback(Feedback feedback);
//	public abstract List<User> getUser();
	public abstract int deleteOrderById(Integer  oNumber);
	//����pid��ȡschid
	public abstract perioddivision getpd(int pid);
	////����schid��ȡsche
	public abstract Sch getsche(int schid);
}
