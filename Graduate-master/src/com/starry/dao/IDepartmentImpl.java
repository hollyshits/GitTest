package com.starry.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.starry.entity.Department;
@Repository("departmentDao")
public class IDepartmentImpl extends SqlSessionDaoSupport implements IDepartmentDao {

	@Resource
    public void setSuperSessionFactory(SqlSessionFactory sessionFactory){
        this.setSqlSessionFactory(sessionFactory);
    }

	@Override
	public List<Department> getRandomDepart() {
		return this.getSqlSession().selectList("getRandomDepart");
	}

	@Override
	public List<Department> getAll(int pageNum,int pageSize) {
		 PageHelper.startPage(pageNum,pageSize);
		return getSqlSession().selectList("getcla");
	}
	@Override
	public int DeleteById(String id) {
		int result = this.getSqlSession().delete("delById", id);
		return result;
	}

	@Override
	public Department getDepartById(Integer dNumber) {
		return this.getSqlSession().selectOne("getDepartById",dNumber);
	}

	@Override
	public List<Department> getAllDepart() {
		return this.getSqlSession().selectList("getAllDepart");
	}

	@Override
	public List<Department> findName(String info,int pageNum,int pageSize) {
		 PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getByName", info);
	}
	@Override
	public List<Department> findId(String info,int pageNum,int pageSize) {
		 PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getById", info);
	}
	@Override
	public void updateDepart(Department department) {
		this.getSqlSession().update("updateById",department );
		
	}
	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		 return getSqlSession().selectList("getcla");
	}
	@Override
	public List<Department> findId(Integer cNumber) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("getById", cNumber);
	}

}
