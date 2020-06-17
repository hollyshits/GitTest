<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">

<head>
	<meta charset="UTF-8">
	<title>H医院预约平台 - 按医生挂号</title>
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/ui.css">
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/hospital.css">
</head>
<body>
<jsp:include page="include/head.jsp"/>
<%--	<div id="top" class="top">
		<div class="wrap">
			<p class="call">010-114/116114电话预约</p>
			<p class="welcome">欢迎来到城市医院预约挂号统一平台&nbsp;请&nbsp;
			<a href="#">登录</a>
			<a href="#">注册</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#">帮助中心</a>
			</p>
		</div>
	</div>--%>


	<div id="header" class="header">
		<div class="wrap clearfix">
			<a class="logo" href="shouye.jsp"><img src="./images/logo.jpg"></a>

			<div class="search ui-search">

				<div class="ui-search-selected">医院</div>
				<div class="ui-search-select-list">
					<a href="#1">科室</a>
					<a href="#1">疾病</a>
					<a href="#1">医院</a>
				</div>
				<input type="text" name="search-content"  class="ui-search-input" placeholder="请输入搜索内容">
				<a href="#" class="ui-search-submit">&nbsp;</a>

			</div>
		</div>

	</div>

	<div id="nav" class="nav">
		<div class="wrap">

			<a href="shouye.jsp" class="link">首页</a>
			<a href="./getAllDepart" class="link link_focus">按科室挂号</a>
			<a href="#" class="link">按医生挂号</a>
			<a href="#" class="link">按疾病挂号</a>
			<a href="#" class="link">最新公告</a>
			<a href="#" class="link right">社会知名医生</a>

		</div>
	</div>

	
	<div id="filter" class="filter wrap">
		<div class="group">

			<div class="caption">
				科室类型
				<a class="condition condition_focus" href="#">全部</a>
			</div>
		
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>
			<a class="condition" href="#">卫生部直属医院</a>

		</div>
		<div class="group">

			<div class="caption">
				科室等级
				<a class="condition condition_focus" href="#">全部</a>
			</div>
		
			<a class="condition" href="#">卫生部直属医院</a>

		</div>
		<div class="group">

			<div class="caption">

				<a class="condition condition_focus" style="display: none" href="#">全部</a>
			</div>

			<a class="condition" href="#">卫生部直属医院</a>

		</div>

	</div>

    <script type="text/template" id="template_datalist_ite">
		<c:forEach items="${PageInfo.list}" var="alldoctor">

		<div class="item">
			<div class="cover"><img src="${alldoctor.image}" alt="医院a" /></div>
			<div class="name">
				${alldoctor.cName}
				<span class="level"></span>
			</div>
			<div class="time">所属科室:${alldoctor.dName}</div>
			<div class="phone">电话：${alldoctor.dTel}</div>
			<div class="address">职称：${alldoctor.dResume}</div>
			<button  class="button" onclick='appointdoc("${user.uname}","${alldoctor.dNumber}","${user.integrity}")'>&nbsp;</button>
		</div>
		</c:forEach>
    </script>
<script type="text/javascript">
	function appointdoc(uname,dnumber,integrity) {
	    console.log(integrity)
        console.log(dnumber)
		if(uname=="") {
            con=confirm("请先登录");
            if(con==true){
                location.href = "login2.jsp";
            }
        }else if (integrity<60){
            con=confirm("您的信誉分不足60，请联系管理员");
            if(con==true){
                location.href = "shouye.jsp";
            }
		}
        else{
		    location.href="./getdoc?doctornum="+dnumber;
		}
    }

</script>
	<div id="datalist" class="datalist wrap">

		<div class="item">
			<div class="cover"><img src="images/hospital-1.jpg" alt="医院a" /></div>
			<div class="name">
				首都医科大学附属北京同仁医院
				<span class="level">三级甲等</span>
			</div>
			<div class="time">放号时间 8:30</div>
			<div class="phone">电话：010-1234567</div>
			<div class="address">地址：北京市东城区东交民巷1号(西区)；北京市东城区崇文门内大街8号(东区)</div>
			<a  class="button" href="#">&nbsp;</a>
		</div>

		<div class="item">
			<div class="cover"><img src="images/hospital-1.jpg" alt="医院a" /></div>
			<div class="name">
				首都医科大学附属北京同仁医院
				<span class="level">三级甲等</span>
			</div>
			<div class="time">放号时间 8:30</div>
			<div class="phone">电话：010-1234567</div>
			<div class="address">地址：北京市东城区东交民巷1号(西区)；北京市东城区崇文门内大街8号(东区)</div>
			<a  class="button" href="#">&nbsp;</a>
		</div>
		<div class="item">
			<div class="cover"><img src="images/hospital-1.jpg" alt="医院a" /></div>
			<div class="name">
				首都医科大学附属北京同仁医院
				<span class="level">三级甲等</span>
			</div>
			<div class="time">放号时间 8:30</div>
			<div class="phone">电话：010-1234567</div>
			<div class="address">地址：北京市东城区东交民巷1号(西区)；北京市东城区崇文门内大街8号(东区)</div>
			<a  class="button" href="#">&nbsp;</a>
		</div>


	</div>

	<div id="pagination" class="pagination wrap">
		<a href="#0" class="item item_text item_first">首页</a>

		<a href="#0" class="item item_icon item_prev">&nbsp;</a>

		<span class="page_wrap">
			<a href="#1" class="item item_page">1</a>
			<a href="#1" class="item item_page item_page_focus">2</a>
			<a href="#1" class="item item_page">2</a>
		</span>

		<a href="#0" class="item item_icon item_next">&nbsp;</a>

		<a href="#0" class="item item_text item_last">尾页</a>

		<a href="#0" class="item item_count">共计3页</a>
		<span href="#0" class="item item_text">到
			<input type="text" class="input_page" name="input_page" />
			<input type="button" class="input_submit" value="" />
		</span>

	</div>

	<div id="footer" class="footer">
		Copyright ©Lyx
	</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/ui.js"></script>
	<script type="text/javascript" src="js/data.js"></script>
	<script type="text/javascript" src="js/hospital.js"></script>
</body>
</html>