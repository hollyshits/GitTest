package com.starry.dao;

import java.util.List;

import com.starry.entity.*;

public interface IDoctorDao {
	public abstract List<Order> getOrderBySnumber(Integer sNumber);

	public abstract List<Sch> getdocSchebydate(String date);

	public abstract List<Comment> listChildComment(Integer id);

	public abstract void deleteComment(Integer id);

	public abstract Comment getCommentById(Integer id);

	public abstract void updateArticle(Article article);

	public abstract void updateCommentCount(Integer id);

	public abstract void insertComment(Comment comment);

	public abstract List<Comment> listCommentByArticleId(Integer id);

	public abstract Article getArticleByStatusAndId(Integer id);

	
	public abstract Doctor login(Doctor doctor);

	public abstract List<Order> gettodayorder(Integer docid,String s);


	public abstract List<DoctorInfo> selectAll(int pageNum,int pageSize);//

	public abstract int deleteById(Integer number);//

	//��ȡҽ����Sche
	public abstract List<Sch> getdocSche(Integer dNumber);
	//����sche��ȡҽ����perioddivision
	public abstract List<perioddivision> getdocPv(int schid);


	public abstract String getDname(Integer cNumber);

	public abstract Doctor getBynum(Integer dNumber);
	//����dNumber��ȡҽ�����Ű���Ϣ
	public abstract List<Sch> getSchs(Integer dNumber);
	//��ȡ��ҽ���ķ�ʱ��ԤԼ��Ϣ
	public abstract List<perioddivision> getPvs(String schid);

	//�鿴ָ��order;
	public  abstract Order getorder(Integer schid,String timediv);

	public abstract int insert(Doctor doctor);//

	public abstract int update(Doctor doctor);//

	public abstract List<Doctor> getByDepart(Integer cNumber);

	public abstract void changePvStatus(Integer oNumber, Integer status);
	public abstract void changeOrderStatus(Integer oNumber,Integer status);


	public abstract List<Doctor> getById(Integer id);
	
	public abstract List<DoctorInfo> findName(String info,int pageNum,int pageSize);
	public abstract List<DoctorInfo> findId(String info,int pageNum,int pageSize);
	public abstract List<DoctorInfo> findDepartName(String info,int pageNum,int pageSize);
	
	public abstract int insertSch(Sch sch);
	public abstract int insertperioddivision(perioddivision per);
	public abstract List<DepartmentInfo> getD(String dNumber);
	
	public abstract List<Sch> getSInfo(Integer sNumber);
	
	public abstract List<OrderInfo> getOrderByDoctor(String sNumber,int pageNum,int pageSize);
	
	
	//json
	public abstract List<DoctorInfoJson> getJsonDoctor(DoctorInfoJson doctorInfoJson);
	
	public abstract List<Jsondoctor> getAllJsonDoctor(String cNumber);
	public abstract List<Jsondoctor> getJsonDoctorByName(String name);
}
