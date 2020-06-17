package com.starry.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.starry.entity.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;

@Repository("doctorDao")
public class IDoctorImpl extends SqlSessionDaoSupport implements IDoctorDao {
	  @Resource
	    public void setSuperSessionFactory(SqlSessionFactory sessionFactory){
	        this.setSqlSessionFactory(sessionFactory);
	    }
	  
	@Override
	public List<DoctorInfo> selectAll(int pageNum,int pageSize) {
		List<DoctorInfo> list = this.getSqlSession().selectList("getAllDoctor");
		 PageHelper.startPage(pageNum,pageSize);
		return list;
	}

    //����dNumber��ȡҽ��
    @Override
    public Doctor getBynum(Integer dNumber){
	      Doctor doctor=this.getSqlSession().selectOne("getDoctorById",dNumber);
	      return doctor;
    }

    @Override
    public List<Sch> getSchs(Integer dNumber) {
        List<Sch> schs=new ArrayList<>();
        schs=this.getSqlSession().selectList("getSchs",dNumber);
        return schs;
    }
	//��ȡ��ҽ���ķ�ʱ��ԤԼ��Ϣ
	@Override
	public List<perioddivision> getPvs(String schid) {
	  	List<perioddivision> pvList=new ArrayList<>();
	  	pvList=this.getSqlSession().selectList("getPvs",schid);
		return pvList;
	}

	@Override
	public Order getorder(Integer schid, String timediv) {
		HashMap<String,String> map=new HashMap<>();
		String s= schid.toString();
		map.put("schid",s);
		map.put("timediv",timediv);
		return this.getSqlSession().selectOne("getorder",map);
	}

	@Override
	public int deleteById(Integer number) {
		int result=this.getSqlSession().delete("deleteByNumber",number);
		return result;
	}
    //��ȡҽ����Sche
	@Override
	public List<Sch> getdocSche(Integer dNumber) {
		return this.getSqlSession().selectList("getdocSche",dNumber);
	}

	@Override
	public List<perioddivision> getdocPv(int schid) {
		return this.getSqlSession().selectList("getdocPv",schid);
	}

	@Override
	public String getDname(Integer cNumber) {
		return this.getSqlSession().selectOne("getDname",cNumber);
	}

	@Override
	public int insert(Doctor doctor) {
		 int result =this.getSqlSession().insert("addDoctor",doctor);
		 return result;
	}

	@Override
	public int update(Doctor doctor) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("updateDoctor", doctor);
	}

	@Override
	public List<Doctor> getByDepart(Integer cNumber) {
		return this.getSqlSession().selectList("getByDepart",cNumber);
	}

	@Override
	public void changePvStatus(Integer oNumber, Integer status) {
		HashMap<String,Integer> map=new HashMap<>();
		map.put("oNumber",oNumber);
		map.put("status",status);
		this.getSqlSession().update("changePvStatus",map);
	}

	@Override
	public void changeOrderStatus(Integer oNumber, Integer status) {
	  	HashMap<String,Integer> map=new HashMap<>();
	  	map.put("oNumber",oNumber);
	  	map.put("status",status);
		this.getSqlSession().update("changeOrderStatus",map);
	}

	@Override
	public List<Doctor> getById(Integer dNumber) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("getDoctorById", dNumber);
	}

	@Override
	public List<DoctorInfo> findName(String info,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		 PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getDoctorByName", info);
	}

	@Override
	public List<DoctorInfo> findId(String info,int pageNum,int pageSize) {
		 PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getDById", info);
	}

	@Override
	public List<DoctorInfo> findDepartName(String info,int pageNum,int pageSize) {
		 PageHelper.startPage(pageNum,pageSize);
		return this.getSqlSession().selectList("getDoctorBycNumber", info);
	}

	@Override
	public List<Order> getOrderBySnumber(Integer sNumber) {
		return this.getSqlSession().selectList("getOrderBySnumber",sNumber);
	}

	@Override
	public List<Sch> getdocSchebydate(String date) {
		return this.getSqlSession().selectList("getdocSchebydate",date);
	}

	@Override
	public List<Comment> listChildComment(Integer id) {
		return this.getSqlSession().selectList("listChildComment",id);
	}

	@Override
	public void deleteComment(Integer id) {
		this.getSqlSession().delete("deleteComment",id);
	}

	@Override
	public Comment getCommentById(Integer id) {
		return this.getSqlSession().selectOne("getCommentById",id);
	}

	@Override
	public void updateArticle(Article article) {
		this.getSqlSession().update("updateArticle",article);
	}

	@Override
	public void updateCommentCount(Integer id) {
		this.getSqlSession().update("updateCommentCount",id);
	}

	@Override
	public void insertComment(Comment comment) {
		this.getSqlSession().insert("insertComment",comment);
	}

	@Override
    public List<Comment> listCommentByArticleId(Integer id) {
        return this.getSqlSession().selectList("listCommentByArticleId",id);
    }

    @Override
	public Article getArticleByStatusAndId(Integer id) {
		System.out.println(111);
		return this.getSqlSession().selectOne("getArticle",id);
	}

	@Override
	public Doctor login(Doctor doctor) {
		Doctor doctor1  = this.getSqlSession().selectOne("doctorlogin",doctor);
		/*if(!list.isEmpty())
			return true;
		return false;*/
		return doctor1;
	}

	@Override
	public List<Order> gettodayorder(Integer docid, String s) {
	  	HashMap<String,String> map=new HashMap<>();
	  	map.put("docid",docid.toString());
	  	map.put("date",s);
		return this.getSqlSession().selectList("gettodayorder",map);
	}

	@Override
	public int insertSch(Sch sch) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("addSch", sch);
	}

	@Override
	public int insertperioddivision(perioddivision per) {
		return this.getSqlSession().update("addper", per);
	}

	@Override
	public List<DepartmentInfo> getD(String dNumber) {
		// TODO Auto-generated method stub
		List<DepartmentInfo> list = this.getSqlSession().selectList("getDe", dNumber);
		System.out.println(list);
		return this.getSqlSession().selectList("getDe", dNumber);
	}

	@Override
	public List<Sch> getSInfo(Integer sNumber) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("getSchInfo",sNumber);
	}

	@Override
	public List<OrderInfo> getOrderByDoctor(String sNumber,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return this.getSqlSession().selectList("getOrderbyDoctor", sNumber);
	}


	@Override
	public List<DoctorInfoJson> getJsonDoctor(DoctorInfoJson doctorInfoJson) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("JsonDoctor", doctorInfoJson);
	}

	@Override
	public List<Jsondoctor> getJsonDoctorByName(String name) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("JsonDoctorByName", name);
	}

	@Override
	public List<Jsondoctor> getAllJsonDoctor(String cNumber) {
		return this.getSqlSession().selectList("JsonAllDoctor",cNumber);
	}

}
