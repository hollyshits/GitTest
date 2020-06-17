package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.DoctorBean;
import com.bean.UserBean;
import com.github.pagehelper.PageInfo;
import com.starry.dao.DoctorDao;
import com.starry.dao.UserDao;
import com.starry.dao.BookDao;
import com.starry.entity.DoctorInfo;
import org.springframework.ui.Model;

//@WebServlet("/getAllDoctors")
public class getAllDoctorServlet extends HttpServlet{
    public getAllDoctorServlet(){

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        DoctorDao doctorDao=new DoctorDao();
        /*List<DoctorBean> list=new ArrayList<>();
        List<DoctorInfo> alldoctor = doctorService.selectAll(pageNum,pageSize);
        PageInfo<DoctorInfo> l = new PageInfo<DoctorInfo>(alldoctor);
        model.addAttribute("PageInfo",list);
        System.out.println("鍒嗛〉鏄剧ず"+list);*/
        try {
            ResultSet rs=doctorDao.getAll();
            while(rs.next()){
               // list.add(rs.get);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
