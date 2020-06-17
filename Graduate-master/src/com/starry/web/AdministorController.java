package com.starry.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.starry.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.starry.service.IAdministorService;
import com.starry.service.IDoctorService;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.DateUtils;
import utils.tt;

@Controller
@RequestMapping("/")
public class AdministorController {
	@Autowired
	private IAdministorService administorService;
	@Autowired
	private IDoctorService doctorService;
	public IAdministorService getAdministorService() {
		return administorService;
	}
	@Resource
	public void setAdministorService(IAdministorService administorService) {
		this.administorService = administorService;
	}
	@Resource
	public void setDoctorService(IDoctorService doctorService) {
		this.doctorService = doctorService;
	}
	public IDoctorService getDoctorService() {
		return doctorService;
	}
//method = RequestMethod.POST)
@RequestMapping(value = "wordcloud", produces = "application/json")
public @ResponseBody
HashMap<String,Integer> getJson5() throws IOException, ParseException {
	HashMap<String,Integer> map=new HashMap<>();
	List<String> list=administorService.getcomments();
	System.out.println(list);
	List<Map.Entry<String, Integer>> s = tt.add(list);
	for(int i=0;i<s.size();i++){
		map.put(s.get(i).getKey(),s.get(i).getValue());
	}
	return map;
}


@RequestMapping(value = "weekdata", produces = "application/json")
	public @ResponseBody
	List<Integer> getJson4() {
		Date date=new Date();
		List<Integer> data=new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		for(int i=-5;i<0;i++){
			String s= DateUtils.getDate(i);
			int k=0;int j=0;
			List<Order> list = administorService.getweekdata(s);
			for(int q=0;q<list.size();q++){
				if(list.get(q).getStatus()==4){
					k++;
					j++;
				}else {
					j++;
				}
			}
			data.add(k);
			data.add(j);
			System.out.println(k+"--"+j);
		}
		return data;
	}




	@RequestMapping(value = "xiaoshou", produces = "application/json")
	public @ResponseBody
	int getJson3(Model model) {
		List<Order> list = administorService.getdindan();
		int money=0;
		for(Order order:list){
			money+=order.getPrice();
		}
		List<Comment> commentList = administorService.listRecentComment(5);
		model.addAttribute("commentList",commentList);
		System.out.println(commentList);
		return money;
	}

	@RequestMapping(value = "dindan", produces = "application/json")
		public @ResponseBody
		List<Order> getJson2() {
			List<Order> list = administorService.getdindan();
			System.out.println(list);
			return list;
		}
	@RequestMapping(value = "dianzan", produces = "application/json")
	public @ResponseBody
	List<Article> dianzan() {
		List<Article> list = administorService.getArticle();
		System.out.println(list);
		return list;
	}

    @RequestMapping("admincomment")
    public String admincomment(Model model){
		PageInfo<Article> articleList = administorService.pageArticle(1, 10);
		model.addAttribute("pageInfo", articleList);
		List<Comment> recentCommentList = administorService.listRecentComment(10);
		model.addAttribute("recentCommentList", recentCommentList);
		return "admincomment";
	}

	@RequestMapping(value = "index")
	public String login(@RequestParam("name") String name,
						@RequestParam("pwd") String pwd, @RequestParam("status") String status, Model model, HttpServletRequest request) {
		if(status.equals("1")){
			boolean result = administorService.checkLogin(name, pwd);
			if (result) {
				Date date=new Date();
				HttpSession session = request.getSession();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				model.addAttribute("time",sdf.format(date));
				model.addAttribute("adminname", name);
				session.setAttribute("adminname", name);
				session.setAttribute("time",sdf.format(date));
				model.addAttribute("pwd", pwd);
				return "adminindex";
			}
		}
		if(status.equals("2")){
			HttpSession session = request.getSession();
			Integer dNumber=Integer.parseInt(name);
			Doctor doctor = doctorService.checklogin(dNumber, pwd);
			if (doctor!=null) {
				model.addAttribute("doc",doctor);
				/*model.addAttribute("docid",doctor.getdNumber());*/
				model.addAttribute("name", name);
				session.setAttribute("doc",doctor);
				return "dindex";
			}
		}
		return "login";
	}

	@RequestMapping(value="addDepart", method = RequestMethod.POST)
	public String adDepart(@RequestParam(value="cNumber") Integer cNumber,@RequestParam(value="dName") String dName,@RequestParam(value="dDec") String dDec){
		Department department = new Department(cNumber,dName,dDec);
		System.out.println(department);
		int result = administorService.addDepart(department);
		return "redirect:/getDepart?pageNum=1&pageSize=2";
	}
	/*@RequestMapping(value="addDoctor", method = RequestMethod.POST)
	public String adDoctor(@RequestParam(value="cNumber") String cNumber,@RequestParam(value="dName") String dName,@RequestParam(value="dDec") String dDec){
		Department department = new Department(cNumber,dName,dDec);
		System.out.println(department);
		int result = administorService.addDepart(department);
		System.out.println(result);
//		if(result==1){
			return "redirect:/getDepart";
//		}
//		return "404";
	}*/
	@RequestMapping(value="updatePwd")
	public String updatePwd(@RequestParam(value="Pwd") String Pwd,@RequestParam(value="aNumber") String aNumber,@RequestParam(value="Name") String name){
		Administor administor = new Administor(aNumber, name, Pwd);
		administorService.updatePwd(administor);
		return "login";
	}
	/**
	 * 
	 */
	@RequestMapping("getAllOreder")
	public String getAllOrder(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		List<OrderInfo> orderinfo = administorService.getAll(pageNum,pageSize);
		PageInfo<OrderInfo> list = new PageInfo<OrderInfo>(orderinfo);
		model.addAttribute("PageInfo",list);
		return "allOreder";
	}
	@RequestMapping("getCount")
	public String getCount(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		List<Count> count = administorService.getCount(pageNum,pageSize);
		PageInfo<Count> list = new PageInfo<Count>(count);
		model.addAttribute("PageInfo",list);
		return "count";
	}
	@RequestMapping("getFeedBack")
	public String getFeedback(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		List<Feedback> feddback = administorService.getFeedBack(pageNum,pageSize);
		PageInfo<Feedback> list = new PageInfo<Feedback>(feddback);
		model.addAttribute("PageInfo",list);
		return "feedback";
	}
	@RequestMapping("addNew")
	public String addNew(@RequestParam(value="title") String title,@RequestParam(value="content") String content,@RequestParam("time") String time){
		New news = new New( title, content, time);
		administorService.addNew(news);
		return "redirect:/getAllNew?pageNum=1&pageSize=3";
	}
	
	@RequestMapping("getAllNew")
	public String getAllNew(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		List<New> news = administorService.getAllNew(pageNum,pageSize);
		PageInfo<New> list = new PageInfo<New>(news);
		model.addAttribute("PageInfo",list);
		return "welcome";
	}
}
