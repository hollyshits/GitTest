package com.starry.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import cn.hutool.http.HtmlUtil;
import com.bean.JsonResult;
import com.bean.ScheBean;
import com.starry.entity.*;
import com.starry.service.ArticleService;
import com.starry.service.IUserService;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.starry.service.IDepartmentService;
import com.starry.service.IDoctorService;
import utils.DateUtils;
import utils.MyUtils;

@Controller
@RequestMapping("/")
public class DoctorController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private IDoctorService doctorService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IUserService iUserService;
	public IUserService getiUserService() { return iUserService; }

	public void setiUserService(IUserService iUserService) { this.iUserService = iUserService; }

	
	public IDoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(IDoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	@RequestMapping(value = "/recommendDoc", produces = "application/json")
	public @ResponseBody List<DoctorInfo> recommendDoc() {
		List<DoctorInfo> list = doctorService.selectAll(1,4);
		System.out.println("DoctorController" + list);
		return list;
	}


	//评论区
@RequestMapping("/getdoccomment")
	public String getdoccomment(@RequestParam("docid") Integer docid,@RequestParam("status") Integer status, Model model){
	/*Article article = articleService.getArticleByStatusAndId(docid);*/
	Article article = doctorService.getArticleByStatusAndId(docid);
	List<Comment> commentList = doctorService.listCommentByArticleId(docid);
	model.addAttribute("commentList", commentList);
	model.addAttribute("article", article);
	if(status==1) {
		return "commentDetail";
	}
	if(status==3) {
		return "commentDetail3";
	}
	return "commentDetail2";
}

	@RequestMapping(value = "/articleview", method = {RequestMethod.POST})
	@ResponseBody
	public String increaseViewCount(@RequestParam("id") Integer id) {
		Article article = doctorService.getArticleByStatusAndId(id);
		Integer articleCount = article.getArticleViewCount() + 1;
		article.setArticleViewCount(articleCount);
		doctorService.updateArticle(article);
		System.out.println(articleCount.toString());
		return articleCount.toString();
	}


	@RequestMapping(value = "/commentdelete")
	public void deleteComment(@RequestParam("id") Integer id) {
		Comment comment = doctorService.getCommentById(id);
		//删除评论
		doctorService.deleteComment(id);
		//删除其子评论
		List<Comment> childCommentList = doctorService.listChildComment(id);
		for (int i = 0; i < childCommentList.size(); i++) {
			doctorService.deleteComment(childCommentList.get(i).getCommentId());
		}
		//更新文章的评论数
		Article article = doctorService.getArticleByStatusAndId(comment.getCommentArticleId());
		doctorService.updateCommentCount(article.getArticleId());
	}

	@RequestMapping(value = "/comment", method = {RequestMethod.POST})
	public JsonResult insertComment(Comment comment) {
		//添加评论
		comment.setCommentCreateTime(new Date());
		comment.setCommentIp("127.0.0.1");
		/*System.out.println(comment.getCommentRole());
		comment.setCommentRole(0);*/
		comment.setCommentAuthorAvatar(MyUtils.getGravatar(comment.getCommentAuthorEmail()));

		//过滤字符，防止XSS攻击
		comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
		comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
		comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
		comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));
		try {
			doctorService.insertComment(comment);

			//更新文章的评论数
			Article article = doctorService.getArticleByStatusAndId(comment.getCommentArticleId());
			doctorService.updateCommentCount(article.getArticleId());
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult().fail();
		}
		return new JsonResult().ok();
	}
	//点赞增加
	@RequestMapping(value = "articlelike", produces = "application/json")
	public @ResponseBody Integer increase(@RequestParam("artId") Integer id) {
		Article article = doctorService.getArticleByStatusAndId(id);
		Integer articleCount = article.getArticleLikeCount() + 1;
		article.setArticleLikeCount(articleCount);
		doctorService.updateArticle(article);
		System.out.println(articleCount);
		return articleCount;
	}


@RequestMapping(value = "Djson", produces = "application/json")
	public @ResponseBody List<DoctorInfo> getJson() {
		List<DoctorInfo> list = doctorService.selectAll(1,4);
		return list;
	}
//管理界面今日排班总览
	@RequestMapping(value = "getdoctodywork", produces = "application/json")
	public @ResponseBody List<Sch> getdoctodywork() {
		Date date=new Date();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(1);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		List<Sch> list = doctorService.getdocSchebydate(sdf.format(date));
		for(Sch s:list) {
			List<Order> list1 = doctorService.getOrderBySnumber(s.getsNumber());
			int i=0,j=0;
			for (Order order:list1){
				if(order.getStatus()==4){
					i++;
					j++;
				}else {
					j++;
				}
			}
			if(j==0){
				s.setPercent("100%");
			}else{
			s.setPercent((((float)i/(float) j)*100)+"%");
			}
			System.out.println(numberFormat.format((float) i / (float) j * 100));
		}
		return list;
	}



@RequestMapping("gettodayorder")
	public String gettodayorder(@RequestParam("sNumber") Integer docid, Model model){
		Date date1=new Date();
	  	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  	String s=dateFormat.format(date1);
		System.out.println(docid+s);
	  	List<Order> orders=doctorService.gettodayorder(docid,s);
		model.addAttribute("orders",orders);
		return "todaywork";
}


	//根据科室来获取医生
@RequestMapping("getdocByDepart")
	public String getdocByDepart(@RequestParam Integer dNumber,Model model){
	Department department=departmentService.getDepartById(dNumber);
	List<Doctor> doctors = doctorService.getByDepart(dNumber);
	model.addAttribute("doctor", doctors);
	System.out.println(department);
	model.addAttribute("depart",department);
	return "officeInfoShow";
}
//更改uorder状态
	@RequestMapping("changeOrderStatus")
	public String changeOrderStatus(@RequestParam("oNumber") Integer oNumber,@RequestParam("status") Integer status){
		doctorService.changeOrderStatus(oNumber,status);
		Order order=iUserService.getOrderById(oNumber);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		integrity inte=new integrity(order.getId(),order.getDocid(),sdf.format(new Date()),"完成预约",10);
		iUserService.minusIntegrity(inte);
		return "welcome";
	}



    //更改医生的perioddivision状态
@RequestMapping("changePvStatus")
	public String changePvStatus(@RequestParam("oNumber") Integer oNumber,@RequestParam("status") Integer status,@RequestParam("schid") Integer schid){
		doctorService.changePvStatus(oNumber,status);
		if(status==0){
			iUserService.minusRest(schid);
		}
		if(status==1){
			iUserService.plusRest(schid);
		}
		return "welcome";
}


	//查看指定order
@RequestMapping("getorder")
	public String getorder(@RequestParam("sid") Integer schid,@RequestParam("timeDiv") String timediv,Model model){
	System.out.println(schid+" "+timediv);
		Order order=doctorService.getorder(schid,timediv);
		User user=iUserService.getuser(order.getId());
		System.out.println(user);
        model.addAttribute("order",order);
		model.addAttribute("userorder",user);
		return "getInfoByorder";
}

	//更具id获取该医生的sche
@RequestMapping("getdocSche")
	public String getdocSche(@RequestParam("dNumber") Integer dNumber,Model model){
		List<Sch> list=new ArrayList<>();
		list=doctorService.getdocSche(dNumber);
		String departname=doctorService.getDname(list.get(0).getcNumber());
		model.addAttribute("Dname",departname);
		model.addAttribute("docScheList",list);
		/*PageContext pageContext;
		pageContext.setAttribute("list",list);*/
		return "dSche";
}
	//根据sche获取医生的perioddivision


	@RequestMapping("getdocPv")
	public String getdocPv(@RequestParam("sid") String sid,Model model){
		int schid=Integer.valueOf(sid);
		List<perioddivision> pl =doctorService.getdocPv(schid);
		model.addAttribute("pvl",pl);
		return "pvtable";
	}

	@RequestMapping(value = "getAllDoctor")
	public  String selectAll(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model) {
		List<DoctorInfo> alldoctor = doctorService.selectAll(pageNum,pageSize);
		PageInfo<DoctorInfo> list = new PageInfo<DoctorInfo>(alldoctor);
		model.addAttribute("PageInfo",list);
		return "allDoctor";
	}
	@RequestMapping(value = "getAllDoctors")
	public  String selectAlldoc(Model model) {
		List<DoctorInfo> alldoctor = doctorService.selectAll(1,10);
		PageInfo<DoctorInfo> list = new PageInfo<DoctorInfo>(alldoctor);
		model.addAttribute("PageInfo",list);
		return "Doctors";
	}
	
	@RequestMapping(value="getById")
	public String getById(Integer dNumber,Model model){
		List<Doctor> doctors = doctorService.getById(dNumber);
		model.addAttribute("doctor", doctors);
		System.out.println("doctor鐨勪俊鎭�"+doctors);
		return "updateDoctor";
	}
	@RequestMapping(value="getByDoctor")
	public String getByDoctor(Integer dNumber,Model model){
		List<Doctor> doctors = doctorService.getById(dNumber);
		model.addAttribute("doctor", doctors);
		System.out.println("doctor鐨勪俊鎭�"+doctors);
		return "getByDoctorInfo";
	}
//, method = RequestMethod.POST
	@RequestMapping(value = "Ddelete")
	public String delete( Integer dNumber) {
		doctorService.deleteById(dNumber);
		return "redirect:/getAllDoctor?pageNum=1&pageSize=5";
	} 
	
//, method = RequestMethod.POST
	@RequestMapping(value = "addDoctor",method = RequestMethod.POST)
	public String register(@RequestParam("image") MultipartFile image,
			 HttpServletRequest request,
			@RequestParam("dNumber") Integer dNumber,
			@RequestParam("name") String name,
			@RequestParam("dPwd") String dPwd,
			@RequestParam("cNumber") Integer cNumber,
			@RequestParam("dInfo") String dInfo,
			@RequestParam("dResume") String dResume,
			@RequestParam("dTel") String dTel,
			@RequestParam("dEmail") String dEmail,
			Model model) {
		// 鑾峰彇椤圭洰鐨勬牴璺緞锛屽皢涓婁紶鍥剧墖鐨勮矾寰勪笌鎴戜滑鐨勮祫婧愯矾寰勫湪涓�璧凤紝鎵嶈兘鏄剧ず
		HttpSession session = request.getSession();
		String path = session.getServletContext().getRealPath("/");
		System.out.println("getRealPath('/'):" + path);
		int end = path.indexOf("t", 19);
//		String prePath = path.substring(0, end);
//		String realPath = "d:\\temp";
		String prePath = System.getProperty("user.dir");
		String realPath = prePath+"\\WebContent\\images";
		System.out.println("褰撳墠鐨勯」鐩殑璺姴"+realPath);
		String picName = new Date().getTime() + ".jpg";
		if (!image.isEmpty()) {
			try {
				FileUtils.copyInputStreamToFile(image.getInputStream(),
						new File(realPath, picName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		Doctor doctor = new Doctor(dNumber, name, dPwd, cNumber, dInfo,
				dResume, dTel, dEmail, "\\" + picName);
		System.out.println(doctor);
		int a = doctorService.insert(doctor);
		System.out.println("" + a);
		if (a == 1) {
			return "redirect:/getAllDoctor?pageNum=1&pageSize=2";
		}
		return "404";
	}
	@RequestMapping(value = "updateDoctor",method = RequestMethod.POST)
	public String update(@RequestParam("dNumber") Integer dNumber,
			@RequestParam("name") String name,
			@RequestParam("cNumber") Integer cNumber,
			@RequestParam("dInfo") String dInfo,
			@RequestParam("dResume") String dResume,
			@RequestParam("dTel") String dTel,
			@RequestParam("dEmail") String dEmail) {
		Doctor doctor = new Doctor(dNumber, name, cNumber, dInfo, dResume, dTel, dEmail);
		System.out.println("淇敼鐨勫尰鐢熶俊鎭細"+doctor);
		doctorService.update(doctor);
		return "redirect:/getAllDoctor?pageNum=1&pageSize=3";
	}
			
	@RequestMapping(value = "findDoctor")
	public String findDoctor(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model, String info, String chose) {
		// chose=1,鎸夌収绉戝鎼滅储
		// 2濮撳悕鎼滅储
		System.out.println(info + chose);
		if (chose.equals("1")) {
			List<DoctorInfo> dInfos = doctorService.findId(info,pageNum,pageSize);
			PageInfo<DoctorInfo> list = new PageInfo<DoctorInfo>(dInfos);
			model.addAttribute("PageInfo",list);
			return "allDoctor";
		}
		if (chose.equals("2")) {
			List<DoctorInfo> dInfos = doctorService.findName(info,pageNum,pageSize);
			PageInfo<DoctorInfo> list = new PageInfo<DoctorInfo>(dInfos);
			model.addAttribute("PageInfo",list);
			return "allDoctor";
		}
		if(chose.equals("3")){
			List<DoctorInfo> dInfos = doctorService.findDepartName(info,pageNum,pageSize);
			PageInfo<DoctorInfo> list = new PageInfo<DoctorInfo>(dInfos);
			model.addAttribute("PageInfo",list);
			return "allDoctor";
		}
		return "404";
	}
	@RequestMapping(value = "getD")
	public String getDe(@RequestParam("doctorname") String dNumber,Model model){
		System.out.println(dNumber);
		List<DepartmentInfo> departmentInfos = doctorService.getD(dNumber);
		model.addAttribute("department", departmentInfos);
		return "total";
	}

	@RequestMapping(value = "getdoc")
	public String getdoc(@RequestParam("doctornum") Integer docnum,Model model) throws IOException {
		Doctor doctor=doctorService.getBynum(docnum);
		List<Sch> schs=doctorService.getSchs(docnum);
		model.addAttribute("schs",schs);
		List<String> times=new ArrayList<>();
		HashSet<String> hs=new HashSet<>();
		for(Sch sch:schs){
			if(!hs.contains(sch.getsTime().split(" ")[0])){
				times.add(sch.getsTime().split(" ")[0]);
				hs.add(sch.getsTime().split(" ")[0]);
			}
        }
        //获取该医生的分时段预约表
		/*List<perioddivision> pvList=doctorService.getPvs(sch)*/
		model.addAttribute("times",times);
        model.addAttribute("doctor",doctor);
		Department department=departmentService.getDepartById(doctor.getcNumber());
		model.addAttribute("depart",department);
		return "yuyue2";
	}
	//点击时间获取该医生的排班分时段号源
	@RequestMapping("/getSchePeriod")
	public String getSchePeriod(String schid,HttpServletResponse resp,Model model){
		System.out.println(schid);
		List<perioddivision> pvs=doctorService.getPvs(schid);
		System.out.println(pvs);
		model.addAttribute("pvs",pvs);
		return "yuyue3";
	}

	//医生添加排班信息
	@RequestMapping(value="addSch")
	public String addSch(@RequestParam("dNumber") Integer dNumber,@RequestParam("cNumber") Integer cNumber,
			@RequestParam("total") int total,@RequestParam("price") Integer price,@RequestParam("sTime") String sTime,
			@RequestParam("eTime") String eTime){
		int status=1;
		int sNumber=1;
		Sch sch = new Sch(sNumber,dNumber, cNumber, total, price, sTime, eTime,status,total);
		doctorService.insertSch(sch);
		int sid=sch.getsNumber();
		String[] str=sTime.split(" ");
		String[] str1=eTime.split(" ");

		List<String> list = DateUtils.getIntervalTimeList(str[1], str1[1], total);
		for (String s : list) {
			s=str[0]+" "+s;
			Integer pid=null;
			perioddivision per=new perioddivision(pid,sid,sTime,s,status,dNumber);
			doctorService.insertperioddivision(per);
		}
		//doctorService.insertperioddivison(sch.getsNumber(),)
	return "redirect:/getS?sNumber="+sid;
	}
	@RequestMapping(value="getS")
	public String getSInfo(@RequestParam("sNumber") Integer sNumber,Model model){
		List<Sch> list = doctorService.getSInfo(sNumber);
		System.out.println(list);
		model.addAttribute("sch", list);
		return "success";
	}
	
	@RequestMapping("getOrederByDoctor")
	public String getOrederByDoctor(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("sNumber") String sNumber,Model model){
		List<OrderInfo> orderinfo = doctorService.getOrderByDoctor(sNumber, pageNum, pageSize);
		PageInfo<OrderInfo> list = new PageInfo<OrderInfo>(orderinfo);
		model.addAttribute("PageInfo",list);
		return "orderByDoctor";
	}

}
