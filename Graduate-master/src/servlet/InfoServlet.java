package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ScheBean;
import com.bean.UserBean;
import com.starry.dao.UserDao;
import com.starry.dao.BookDao;
import com.starry.dao.DoctorDao;

@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//设置编码类型
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		DoctorDao doctordao = new DoctorDao();
		ResultSet rs=null;
		System.out.println("-------------");
		//为了区分借书和还书的功能，设置tip，tip为1，表示借书
		String sid = request.getParameter("sid");
		HttpSession session = request.getSession();
		try {
			 rs=doctordao.getinfo(sid);
			 while(rs.next()) {
				 session.setAttribute("dnumber", rs.getString(1));
				 session.setAttribute("name", rs.getString(2));
				 session.setAttribute("dpwd", rs.getString(3));
				 session.setAttribute("cnumber", rs.getString(4));
				 session.setAttribute("dinfo", rs.getString(5));
				 session.setAttribute("dresume", rs.getString(6));
				 session.setAttribute("dtel", rs.getString(7));
				 session.setAttribute("demail", rs.getString(8));
			 }
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
			response.sendRedirect("/Graduate/getDoctorInfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		DoctorDao doctordao = new DoctorDao();
		ResultSet rs=null;
		System.out.println("------------");
		//为了区分借书和还书的功能，设置tip，tip为1，表示借书
		String sid = request.getParameter("sid");
		HttpSession session = request.getSession();
		try {
			 rs=doctordao.getinfo(sid);
			 while(rs.next()) {
				 session.setAttribute("dnumber", rs.getString(1));
				 session.setAttribute("name", rs.getString(2));
				 session.setAttribute("dpwd", rs.getString(3));
				 session.setAttribute("cnumber", rs.getString(4));
				 session.setAttribute("dinfo", rs.getString(5));
				 session.setAttribute("dresume", rs.getString(6));
				 session.setAttribute("dtel", rs.getString(7));
				 session.setAttribute("demail", rs.getString(8));
			 }
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
			response.sendRedirect("/Graduate/getDoctorInfo.jsp");
	}

}
