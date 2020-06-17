package com.starry.dao;

import java.util.List;

import net.sf.jsqlparser.statement.delete.Delete;

import com.starry.entity.Department;

public interface IDepartmentDao {
	public abstract List<Department> getRandomDepart();

	public abstract List<Department> getAll(int pageNum,int pageSize);
	public abstract List<Department> getAll();
	public abstract int DeleteById(String id); 

	public abstract Department getDepartById(Integer dNumber);

	//��ȡ���п����б�
	public abstract List<Department> getAllDepart();
	public abstract List<Department> findName(String info,int pageNum,int pageSize);
	public abstract List<Department> findId(String info,int pageNum,int pageSize);
	public abstract List<Department> findId(Integer cNumber);
	
	public abstract void updateDepart(Department department);
}
