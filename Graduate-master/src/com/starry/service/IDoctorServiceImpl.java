package com.starry.service;

import java.util.List;

import javax.annotation.Resource;

import com.starry.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starry.dao.IDoctorDao;

@Service("doctorService")
public class IDoctorServiceImpl implements IDoctorService {
	@Autowired
	private IDoctorDao doctorDao;
	
	public IDoctorDao getDoctorDao() {
		return doctorDao;
	}
	 @Resource
	public void setDoctorDao(IDoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
	@Override
	public List<DoctorInfo> selectAll(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		System.out.println("IDoctorService"+doctorDao.selectAll(pageNum,pageSize));
		return doctorDao.selectAll(pageNum,pageSize);
	}

	@Override
	public List<Order> gettodayorder(Integer docid, String s) {
		return doctorDao.gettodayorder(docid,s);
	}

	@Override
	public Doctor getBynum(Integer id){
		return doctorDao.getBynum(id);
	}

	//����dNumber��ȡҽ�����Ű���Ϣ
	@Override
	public List<Sch> getSchs(Integer dNumber) {
		return doctorDao.getSchs(dNumber);
	}
	//��ȡ��ҽ���ķ�ʱ��ԤԼ��Ϣ
	@Override
	public List<perioddivision> getPvs(String schid) {
		return doctorDao.getPvs(schid);
	}

	@Override
	public int deleteById(Integer number) {
		// TODO Auto-generated method stub
		return doctorDao.deleteById(number);
	}

	@Override
	public int insert(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorDao.insert(doctor);
	}

	@Override
	public int update(Doctor doctor) {
		return doctorDao.update(doctor);
	}

	@Override
	public List<Doctor> getByDepart(Integer cNumber) {
		return doctorDao.getByDepart(cNumber);
	}

	@Override
	public void changePvStatus(Integer oNumber, Integer status) {
		doctorDao.changePvStatus(oNumber,status);
	}

	@Override
	public void changeOrderStatus(Integer oNumber, Integer status) {
		doctorDao.changeOrderStatus(oNumber,status);
	}

	@Override
	public List<Doctor> getById(Integer dNumber) {
		// TODO Auto-generated method stub
		return doctorDao.getById(dNumber);
	}
	//��ȡҽ����Sche
	@Override
	public List<Sch> getdocSche(Integer dNumber) {
		return doctorDao.getdocSche(dNumber);
	}

	//����sche��ȡҽ����perioddivision
	@Override
	public List<perioddivision> getdocPv(int schid) {
		return doctorDao.getdocPv(schid);
	}

	@Override
	public String getDname(Integer cNumber) {
		return doctorDao.getDname(cNumber);
	}

	@Override
	public List<DoctorInfo> findName(String info,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return doctorDao.findName(info, pageNum, pageSize);
	}
	@Override
	public List<DoctorInfo> findId(String info,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return doctorDao.findId(info,pageNum,pageSize);
	}
	@Override
	public List<DoctorInfo> findDepartName(String info,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return doctorDao.findDepartName(info,pageNum,pageSize);
	}

	@Override
	public List<Order> getOrderBySnumber(Integer sNumber) {
		return doctorDao.getOrderBySnumber(sNumber);
	}

	@Override
	public List<Sch> getdocSchebydate(String date) {
		return doctorDao.getdocSchebydate(date);
	}

	@Override
	public List<Comment> listChildComment(Integer id) {
		return doctorDao.listChildComment(id);
	}

	@Override
	public void deleteComment(Integer id) {
		doctorDao.deleteComment(id);
	}

	@Override
	public Comment getCommentById(Integer id) {
		return doctorDao.getCommentById(id);
	}

	@Override
	public void updateArticle(Article article) {
		 doctorDao.updateArticle(article);
	}

	@Override
	public void updateCommentCount(Integer id) {
		doctorDao.updateCommentCount(id);
	}

	@Override
	public void insertComment(Comment comment) {
		doctorDao.insertComment(comment);
	}

	@Override
    public List<Comment> listCommentByArticleId(Integer id) {
        return doctorDao.listCommentByArticleId(id);
    }

    @Override
	public Article getArticleByStatusAndId(Integer id) {
		System.out.println(111);
		return doctorDao.getArticleByStatusAndId(id);
	}

	@Override
	public Doctor checklogin(Integer dNumber, String pwd) {
		Doctor doctor = new Doctor(dNumber, pwd);
		return doctorDao.login(doctor);
	}
	@Override
	public int insertSch(Sch sch) {
		// TODO Auto-generated method stub
		return doctorDao.insertSch(sch);
	}

	@Override
	public int insertperioddivision(perioddivision per) {
		return doctorDao.insertperioddivision(per);
	}

	//�鿴ָ��order;
	@Override
	public Order getorder(Integer schid, String timediv) {
		return doctorDao.getorder(schid,timediv);
	}

	@Override
	public List<DepartmentInfo> getD(String dNumber) {
		// TODO Auto-generated method stub
		return doctorDao.getD(dNumber);
	}
	@Override
	public List<Sch> getSInfo(Integer sNumber) {
		// TODO Auto-generated method stub
		return doctorDao.getSInfo(sNumber);
	}
	@Override
	public List<OrderInfo> getOrderByDoctor(String sNumber,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return doctorDao.getOrderByDoctor(sNumber,pageNum,pageSize);
	}
	@Override
	public List<DoctorInfoJson> JsonDoctor(String dNumber,String cNumber) {
		DoctorInfoJson doctorInfoJson = new DoctorInfoJson(dNumber, cNumber);
		return doctorDao.getJsonDoctor(doctorInfoJson);
	}
	@Override
	public List<Jsondoctor> JsonDoctorByName(String name) {
		// TODO Auto-generated method stub
		return doctorDao.getJsonDoctorByName(name);
	}
	@Override
	public List<Jsondoctor> getAllJsonDoctor(String cNumber) {
		// TODO Auto-generated method stub
		return doctorDao.getAllJsonDoctor(cNumber);
	}

}
