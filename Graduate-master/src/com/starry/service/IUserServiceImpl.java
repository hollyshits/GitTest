package com.starry.service;

import java.util.List;

import javax.annotation.Resource;

import com.starry.entity.*;
import org.springframework.stereotype.Service;

import com.starry.dao.IUserDao;

@Service("userService")
public class IUserServiceImpl implements IUserService {
	 private IUserDao userDao;
	 
	public IUserDao getUserDao() {
		return userDao;
	}
	 @Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void minusIntegrity(integrity inte) {
		userDao.minusIntegrity(inte);
	}

	@Override
	public void plusRest(Integer sNumber) {
		userDao.plusRest(sNumber);
	}

	@Override
	public void minusRest(Integer schid) {
		userDao.minusRest(schid);
	}

	@Override
	public void changeOrderstatus(Integer oid, Integer status) {
		userDao.changeOrderstatus(oid,status);
	}

	@Override
	public void changePvstatus(Integer ppid,Integer status) {
		userDao.changePvstatus(ppid,status);
	}

	@Override
	public Department getDepartbydoc(Integer id) {
		return userDao.getDepartbydoc(id);
	}

	@Override
	public List<Doctor> getRandomDocList() {
		return userDao.getRandomDocList();
	}

	@Override
	public Doctor getdoc(Integer id) {
		return userDao.getdoc(id);
	}

	@Override
	public List<Integer> gethisdoc2(Integer id) {
		return userDao.gethisdoc2(id);
	}

	@Override
	public List<Integer> gethisdoc(Integer id) {
		return userDao.gethisdoc(id);
	}

	@Override
	public List<integrity> getUserInter(Integer id) {
		return userDao.getUserInter(id);
	}

	@Override
	public List<Order> selectodayorder(String date) {
		return userDao.selectodayorder(date);
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl"+userDao.selectAll());
		return userDao.selectAll();
	}
	@Override
	public int deleteById(int id) {
		int result = userDao.deleteById(id);
		return result;
	}

	@Override
	public User getuser(Integer id) {
		return userDao.getuser(id);
	}

	@Override
	public int register(User user) {
		int result = userDao.register(user);
	//	System.out.println("RegisterServiceImpl"+result);
		return result;
	}
	@Override
	public List<User> checkLogin(String name, String pwd) {
		// TODO Auto-generated method stub
		User user=  new User();
		user.setUname(name);
		user.setPwd(pwd);
		List<User> result = userDao.userLogin(user);
		return result;
	}

	@Override
	public Order getOrderById(Integer id) {
		return userDao.getOrderById(id);
	}

	@Override
	public int insertOrder(Order order) {
		return userDao.insertOrder(order);
	}
	@Override
	public List<OrderInfo> getOrderInfoById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getOrderInfoById(id);
	}
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
	@Override
	public int insertFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return userDao.insertFeedback(feedback);
	}
	@Override
	public int deleteOrderById(Integer  oNumber) {
		// TODO Auto-generated method stub
		return userDao.deleteOrderById(oNumber);
	}

	@Override
	public perioddivision getpd(int pid) {
		return userDao.getpd(pid);
	}

	@Override
	public Sch getsche(int schid) {
		return userDao.getsche(schid);
	}

	@Override
	public User findUser(String usercode, String userpwd) {
		User user=this.userDao.findUser(usercode, userpwd);
		return user;
	}

}
