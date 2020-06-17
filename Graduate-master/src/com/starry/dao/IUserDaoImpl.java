package com.starry.dao;

import java.util.HashMap;
import java.util.List;









import javax.annotation.Resource;

import com.starry.entity.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class IUserDaoImpl extends SqlSessionDaoSupport implements IUserDao {

    @Resource
    public void setSuperSessionFactory(SqlSessionFactory sessionFactory){
        this.setSqlSessionFactory(sessionFactory);
    }


	@Override
	public Order getOrderById(Integer id) {
		return this.getSqlSession().selectOne("getOrderById",id);
	}

	@Override
	public void plusRest(Integer sNumber) {
		this.getSqlSession().update("plusRest",sNumber);
	}

	@Override
	public void minusIntegrity(integrity inte) {
		this.getSqlSession().insert("minusIntegrity",inte);
	}

	@Override
	public void minusRest(Integer schid) {
		this.getSqlSession().update("minusRest",schid);
	}

	@Override
	public void changeOrderstatus(Integer oid, Integer status) {
		HashMap<String,Integer> map=new HashMap<>();
		map.put("oid",oid);
		map.put("status",status);
		this.getSqlSession().update("changeOrderstatus",map);
	}

	@Override
	public void changePvstatus(Integer ppid,Integer status) {
		HashMap<String,Integer> map=new HashMap<>();
		map.put("ppid",ppid);
		map.put("status",status);
		this.getSqlSession().update("changePvstatus",map);
	}

	@Override
	public Department getDepartbydoc(Integer id) {
		return this.getSqlSession().selectOne("getDepartbydoc",id);
	}

	@Override
	public List<Doctor> getRandomDocList() {
		return this.getSqlSession().selectList("getRandomDocList");
	}

	@Override
	public Doctor getdoc(Integer id) {
		return this.getSqlSession().selectOne("getdoc",id);
	}

	@Override
	public List<integrity> getUserInter(Integer id) {
		return this.getSqlSession().selectList("getUserInter",id);
	}

	@Override
	public List<Integer> gethisdoc2(Integer id) {
		return this.getSqlSession().selectList("gethisdoc2",id);
	}

	@Override
	public List<Integer> gethisdoc(Integer id) {
		return this.getSqlSession().selectList("gethisdoc",id);
	}

	@Override
    public List<Order> selectodayorder(String date) {
        return this.getSqlSession().selectList("selectodayorder",date);
    }

    public List<User> selectAll() {
        // TODO Auto-generated method stub
    	System.out.println("IUserDaoImpl");
        List<User> users = this.getSqlSession().selectList("getAlluser");
        System.out.println("IUserDaoImpl"+users);
        return users;
    }


	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		int result = this.getSqlSession().delete("deleteById", id);
		return result;
	}

	@Override
	public User getuser(Integer id) {
		return this.getSqlSession().selectOne("getuser",id);
	}


	@Override
	public int register(User users) {
		// TODO Auto-generated method stub
		   int result =this.getSqlSession().insert("register",users);
			return result;
	}


	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<User> userLogin(User user) {
		List<User> list = this.getSqlSession().selectList("findUser", user);
		System.out.println("impl"+list);
		return list;
		
	}


	@Override
	public int insertOrder(Order order) {
		  int result =this.getSqlSession().insert("insertOrder",order);
			return result;
	}


	@Override
	public List<OrderInfo> getOrderInfoById(Integer id) {
		return this.getSqlSession().selectList("getOrderByUserId",id);
	}


	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("updateUser", user);
	}


	@Override
	public int insertFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return this.getSqlSession().insert("insertfeedback", feedback);
	}


	@Override
	public int deleteOrderById(Integer  oNumber) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete("deleteOrderByid", oNumber);
	}

	@Override
	public perioddivision getpd(int pid) {
		return this.getSqlSession().selectOne("getpv",pid);
	}

	@Override
	public Sch getsche(int schid) {
		return this.getSqlSession().selectOne("getsche",schid);
	}


	@Override
	public User findUser(String usercode, String userpwd) {
		// TODO �Զ����ɵķ������
		List<User> list= this.getSqlSession().selectList(usercode,userpwd);
		System.out.println(list.get(0).getUname());
		return list.get(0);
	}


}
