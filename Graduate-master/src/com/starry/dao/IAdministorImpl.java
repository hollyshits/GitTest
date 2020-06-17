package com.starry.dao;


import java.util.List;

import javax.annotation.Resource;

import com.starry.entity.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;

@Repository("administorDao")
public class IAdministorImpl extends SqlSessionDaoSupport implements IAdministorDao {
	@Resource
    public void setSuperSessionFactory(SqlSessionFactory sessionFactory){
        this.setSqlSessionFactory(sessionFactory);
    }

	@Override
	public List<Article> getArticle() {
		return this.getSqlSession().selectList("getArticlelikecount");
	}

	@Override
	public List<Comment> listRecentComment(Integer num) {
		return this.getSqlSession().selectList("listRecentComment",num);
	}

	@Override
	public List<Article> findAllcomment() {
		return this.getSqlSession().selectList("findAllcomment");
	}

	@Override
	public List<String> getcomments() {
		return this.getSqlSession().selectList("getcomments");
	}

	@Override
	public List<Order> getweekdata(String s) {
		return this.getSqlSession().selectList("getweekdata",s);
	}

	@Override
	public List<Order> getdindan() {
		return this.getSqlSession().selectList("getdindan");
	}

	@Override
	public boolean login(Administor administor) {
		System.out.println("impl");
		List<Administor> list = this.getSqlSession().selectList("adlogin", administor);
		System.out.println("impl"+list);
		if(!list.isEmpty())
			return true;
		return false;
	}
	@Override
	public int addDepart(Department department) {
		System.out.println("department"+department);
		return this.getSqlSession().insert("adDepart",department);
	}
	@Override
	public int addDoctor(Doctor doctor) {
		return this.getSqlSession().insert("adDoctor",doctor);
	}
	@Override
	public void updatePwd(Administor administor) {
		this.getSqlSession().update("updatePwd", administor);
		
	}
	@Override
	public List<OrderInfo> getAll(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getOrder");
	}
	@Override
	public List<Count> getCount(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getCount");
	}
	@Override
	public List<Feedback> getFeedBack(int pageNum,int pageSize) {
		 PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getFeedBack");
	}
	@Override
	public int addNew(New news) {
		// TODO Auto-generated method stub
		return this.getSqlSession().insert("addNew", news);
	}
	@Override
	public List<New> getAllNew(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("selectNew");
	}
}
