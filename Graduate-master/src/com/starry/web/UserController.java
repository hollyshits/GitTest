package com.starry.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.starry.entity.*;
import com.starry.service.IDepartmentService;
import com.starry.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.starry.service.IUserService;
import utils.tt;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IDoctorService doctorService;

	public IDoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(IDoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

//	private IRegisterService registerService;

//	public IRegisterService getRegisterService() {
//		return registerService;
//	}
//
//	@Resource
//	public void setRegisterService(IRegisterService registerService) {
//		this.registerService = registerService;
//	}

	public IUserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}



	@RequestMapping(value = "findAllUser", method = RequestMethod.GET)
	public String home(Model model) {
		List<User> users = userService.selectAll();
		model.addAttribute("users", users);
		System.out.println("Controller" + users);
		return "home";
	}

	//用户推荐科室
	@RequestMapping(value = "/userrecommendDepart", produces = "application/json")
	public @ResponseBody
	List<Department> getrecDepart(@RequestParam("uname") Integer uid) {
		List<Integer> list=new ArrayList<>();
		List<Department> list1=new ArrayList<>();
		List<Department> list2=new ArrayList<>();
		List<Department> departList=departmentService.getRandomDepart();
		System.out.println(departList);
		if(uid==null){
			return departList;
		}else{
			//获取历史医生
			list=userService.gethisdoc(uid);
			for(Integer i:list){
				//获取挂过医生的患者
				list1.add(userService.getDepartbydoc(i));
			}
			HashSet<Integer> set=new HashSet<>();
			for(Department department:list1){
				if(set.contains(department.getcNumber())){
					continue;
				}else {
					set.add(department.getcNumber());
					list2.add(department);
				}
			}
			//获取患者的医生
		}
		list2.removeAll(departList);
		list2.addAll(departList);
		System.out.println(list2);
		return list2;
	}


	@RequestMapping(value = "/userrecommendDoc", produces = "application/json")
	public @ResponseBody
	List<Doctor> getrecdoc(@RequestParam("uname") Integer uid) {
		List<Integer> list=new ArrayList<>();
		List<List<Integer>> list1=new ArrayList<>();
		List<List<Integer>> list2=new ArrayList<>();
		List<Doctor> res=new ArrayList<>();
		List<Doctor> doctorList=userService.getRandomDocList();
		if(uid==null){
			return doctorList;
		}else{
			//获取历史医生
			list=userService.gethisdoc(uid);
			for(Integer i:list){
				//获取挂过医生的患者
				list1.add(userService.gethisdoc2(i));
			}
			//获取患者的医生
			HashSet<Integer> set=new HashSet<>();
			for(int i=0;i<list1.size()-1;i++){
				for(int j=0;j<list1.get(i).size()-1;j++){
					if(set.contains(list1.get(i).get(j))){
						continue;
					}else {
						list2.add(userService.gethisdoc(list1.get(i).get(j)));
						set.add(list1.get(i).get(j));
					}
				}
			}
			set.clear();
			System.out.println(list2.size());
			for(int i=0;i<list2.size()-1;i++){
				System.out.println(list2.get(i).size());
				for(int j=0;j<list2.get(i).size()-1;j++){
					if(set.contains(list2.get(i).get(j))){
						continue;
					}else {
						System.out.println(userService.getdoc(list2.get(i).get(j)));
						res.add(userService.getdoc(list2.get(i).get(j)));
						set.add(list2.get(i).get(j));
					}
				}
			}
		}
		res.removeAll(doctorList);
		res.addAll(doctorList);
		return res;
	}


/*	@RequestMapping(value="/LoginServlet")
	public String login(Model model) {
		model.addAttribute("id",111);
		return "shouye";
	}*/

	@RequestMapping(value="/integrity")
	public String getIntegrity(@RequestParam("uid") Integer id,Model model) {
		List<integrity> list=userService.getUserInter(id);
		int number=100;
		for(integrity inte:list){
			number+=inte.getScore();
		}
		model.addAttribute("number",number);
		model.addAttribute("allintegrity",list);
		return "integrity";
	}




	@RequestMapping(value="/login.action", method = RequestMethod.POST)
	public String get(String usercode,String userpwd,Model model,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		usercode=(String) req.getParameter("username");
		userpwd=req.getParameter("pwd");
		System.out.println(usercode+userpwd);
		User user=userService.findUser(usercode, userpwd);
		if(user!=null) {
			model.addAttribute("username", user.getUname());
			System.out.println(user.getUname());
			return "/index2";
		}else {
		resp.sendRedirect("index.jsp");
		return "/login";
		}
	}
	//撤销预约->改变预约状态->减少信誉分->号源释放
	@RequestMapping("/changeorder")
	public void changeorder(@RequestParam("oid") Integer oid,@RequestParam("docid") Integer docid,@RequestParam("id") Integer id){
		userService.changeOrderstatus(oid,3);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		integrity inte=new integrity(id,docid,sdf.format(new Date()),"撤销预约",-10);
		userService.minusIntegrity(inte);
		Order order=userService.getOrderById(oid);
		userService.plusRest(order.getsNumber());
		userService.changePvstatus(order.getPid(),1);
	}


    //患者进行预约
	@RequestMapping("/addappointment")
	public String addapointment(@RequestParam("userid") Integer uid,@RequestParam("pvid") String pid,@RequestParam("message") String info){
		int ppid=Integer.valueOf(pid);
		int onumber=0;
		//更具pid获取ȡperioddivision
		perioddivision pd=userService.getpd(ppid);
		//更具schid获取ȡsche
		Sch sche=userService.getsche(pd.getSchid());
		int id=Integer.valueOf(uid);
		//String date=pd.getSchdate().split(" ")[0]+" "+pd.getTimeDiv();
		String date=pd.getTimeDiv();
		Order order=new Order(onumber,sche.getsNumber(),id,date,sche.getPrice(),1,info,pd.getDocid(),ppid);
		userService.insertOrder(order);
		userService.changePvstatus(ppid,2);
		userService.minusRest(pd.getSchid());
		return "index2";
	}
//��ȡ����Ĺ������
	@RequestMapping(value = "json2", produces = "application/json")
	public @ResponseBody
		List<Order> getJson2() {
		Date date=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Order> list = userService.selectodayorder(dateFormat.format(date));
		return list;
	}



	@RequestMapping(value = "json1", produces = "application/json")
	public @ResponseBody
	List<User> getJson() {
		List<User> list = userService.selectAll();
		System.out.println(list);
		return list;
	}

	@RequestMapping(value="delete" ,method=RequestMethod.POST)
	public String delete(@RequestParam("id") int id){
		userService.deleteById(id);
		System.out.println(id);
		return "success";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@RequestParam("name") String name,
			@RequestParam("pwd") String pwd,
			@RequestParam("tel") String tel,
			@RequestParam("info") String info, Model model) {
		 User user = new User(name, pwd, tel, info);
		 System.out.println(info);
		int a = userService.register(user);
		System.out.println(""+a);
		if(a==1){
			return "success";
		}
		return  "register";
	}
}
