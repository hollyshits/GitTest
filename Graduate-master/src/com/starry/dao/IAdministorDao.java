package com.starry.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.starry.entity.*;

public interface IAdministorDao {
  public abstract List<Article> getArticle();

  public abstract  List<Comment> listRecentComment(Integer num);

  public abstract List<Article> findAllcomment();

  public abstract List<String> getcomments();

  public abstract List<Order> getweekdata(String s);

  public abstract List<Order> getdindan();

  public abstract boolean login(Administor administor); 
  
  public abstract void updatePwd(Administor administor);
  
  public abstract int addDepart(Department department);

  public abstract int addDoctor(Doctor doctor);
  
  public abstract List<OrderInfo> getAll(int pageNum,int pageSize);
  
  public abstract List<Count> getCount(int pageNum,int pageSize);
  
  public abstract List<Feedback> getFeedBack(int pageNum,int pageSize);
  
  public abstract int addNew(New news);
  
  public abstract List<New> getAllNew(int pageNum,int pageSize);
}
