package com.starry.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starry.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starry.dao.IAdministorDao;
import com.starry.dao.IDepartmentDao;

@Service("administorService")
public class IAdministorServiceImpl implements IAdministorService {
@Autowired
	private IAdministorDao administorDao;
	@Autowired
	private IDoctorService doctorService;
	public IDoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(IDoctorService doctorService) {
		this.doctorService = doctorService;
	}
	//@Resource
	public void setAdministorDao(IAdministorDao administorDao) {
		this.administorDao = administorDao;
	}

	public IAdministorDao getAdministorDao() {
		return administorDao;
	}

	@Override
	public List<Article> getArticle() {
		return administorDao.getArticle();
	}

	@Override
	public List<Comment> listRecentComment(Integer num) {
		List<Comment> commentList = null;
		try {
			commentList = administorDao.listRecentComment(num);
			for (int i = 0; i < commentList.size(); i++) {
				Article article = doctorService.getArticleByStatusAndId(commentList.get(i).getCommentArticleId());
				commentList.get(i).setArticle(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentList;
	}

	@Override
	public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<Article> articleList = administorDao.findAllcomment();
		return new PageInfo<>(articleList);
	}

	@Override
	public List<String> getcomments() {
		return administorDao.getcomments();
	}

	@Override
	public List<Order> getweekdata(String s) {
		return administorDao.getweekdata(s);
	}

	@Override
	public List<Order> getdindan() {
		return administorDao.getdindan();
	}

	@Override
	public boolean checkLogin(String name, String pwd) {
		Administor administor = new Administor();
		administor.setName(name);
		administor.setPwd(pwd);
		System.out.println(administor);
		boolean result = administorDao.login(administor);
		return result;
	}

	@Override
	public int addDepart(Department department) {
		System.out.println(administorDao.addDepart(department));
		return administorDao.addDepart(department);
	}

	@Override
	public int addDoctor(Doctor doctor) {
		return administorDao.addDoctor(doctor);
	}

	@Override
	public void updatePwd(Administor administor) {
		administorDao.updatePwd(administor);
	}

	@Override
	public List<OrderInfo> getAll(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return administorDao.getAll(pageNum,pageSize);
	}

	@Override
	public List<Count> getCount(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return administorDao.getCount(pageNum,pageSize);
	}

	@Override
	public List<Feedback> getFeedBack(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return administorDao.getFeedBack(pageNum,pageSize);
	}

	@Override
	public int addNew(New news) {
		// TODO Auto-generated method stub
		return administorDao.addNew(news);
	}

	@Override
	public List<New> getAllNew(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return administorDao.getAllNew(pageNum,pageSize);
	}
}
