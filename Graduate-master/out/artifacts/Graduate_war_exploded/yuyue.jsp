<%@ page import="com.starry.entity.Sch" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>jQuery医生坐诊时间表代码 - 站长素材</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<!--[if lt IE 9]>
	<meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->
<link rel="stylesheet" type="text/css" href="css/index.css">

</head>
<body>
<style>
	.black_overlay{
		display: none;
		position: absolute;
		top: 0%;
		left: 0%;
		width: 100%;
		height: 100%;
		background-color: black;
		z-index:1001;
		-moz-opacity: 0.8;
		opacity:.80;
		filter: alpha(opacity=88);
	}
	.white_content {
		display: none;
		position: absolute;
		top: 25%;
		left: 25%;
		width: 55%;
		height: 55%;
		padding: 20px;
		border: 10px solid orange;
		background-color: white;
		z-index:1002;
		overflow: auto;
	}
</style>
<div style="text-align:center;clear:both"></div>

<div id="light" class="white_content">
	这是一个层窗口示例程序.
	<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">点这里关闭本窗口</a>
	</br>
	<!-- 登陆部分代码 -->
	<form action="1.html" method="post">
		<input  type="radio" name="ra" value="1" />
		<input  type="radio" name="ra" value="2" />
		<input type="submit" value="确定"/>
	</form>

</div>
<script>
    $(document).ready(function () {
        $('.pop-close').click(function () {
            $('.bgPop,.pop').hide();
        });
        $('.click_pop').click(function () {
            $('.bgPop,.pop').show();
        });
    })
</script>
<div class="hd">
	<div class="hd_bg">
		<img src="images/bg.png">
	</div>
	<div class="hd_con">
		<div class="con clearfix">
			<div class="hd_head float-left">
				<img src="images/doctor.png">
			</div>
			<div class="hd_info float-left">
				<div class="doctor_name">
					${doctor.name}
				</div>
				<div class="doctor_address">
					坐诊地点：出诊地点
				</div>
				<div class="doctor_speciality">
					擅长领域：擅长领域
				</div>
				<div class="doctor_experience">
					医生经历：医生经历
				</div>
			</div>
		</div>
	</div>
</div>

<div class="line"></div>

<div class="doctor_con">
	<div class="con">
		<div class="tab">
			<div class="tab_hd clearfix text-center">
				<div class="active float-left"><a href="#doctor_index">医生首页</a></div>
				<div class="float-left"><a href="#doctor_evaluate">就诊评价</a></div>
			</div>
			<div class="tab_con">
				<div id="doctor_index" class="doctor_pane active">
					<table id="tab1" border="0" cellspacing="0" cellpadding="0" class="doctor_table" width="1200">
						<tr>
							<td class="tb_header">时间地点</td>
							<td colspan="9"><div>H医院</div></td>
						</tr>
						<tr>
							<td class="tb_header">时段</td>
							<td rowspan="4" class="arrow_left"><div><img src="images/left.png"></div></td>
							<td rowspan="4" class="arrow_right right_active"><div><img src="images/right_active.png"></div></td>
						</tr>
						<tr>
							<td class="tb_header">上午</td>
						</tr>
						<tr>
							<td class="tb_header">下午</td>
						</tr>
						<tr>
							<td class="tb_header">夜诊</td>
						</tr>
					</table>
				</div>
				<div>
				</div>
				<div id="doctor_evaluate" class="doctor_pane">
					这里是就诊评价的内容
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jquery.min1.js"></script>
<%--<script type="text/javascript" src="js/script.js"></script>--%>
<script>
	$(function() {
        var array = new Array();
        //console.info("info");
        <c:forEach items="${times}" var="item" varStatus="status" >
        array.push("${item}");
        </c:forEach>
    var d = 7;//需要显示的预约天数
    //获取几天后（前）的日期以及周几
    function fun_date(aa) {
        //当前时间
        var date1 = new Date(),
            time1 = date1.getFullYear() + "-0" + (date1.getMonth() + 1) + "-" + date1.getDate(); //time1表示当前时间
        //n天之后的时间
        var date2 = new Date(date1);
        date2.setDate(date1.getDate() + aa);
        var time2 = (date2.getMonth() + 1)+ "-" + date2.getDate();
        var time3=date2.getFullYear() + "-0" + (date2.getMonth() + 1) + "-" + date2.getDate();
        var w = "";
        switch(date2.getDay()) {
            case 0:
                w = "周日"
                break;
            case 1:
                w = "周一"
                break;
            case 2:
                w = "周二"
                break;
            case 3:
                w = "周三"
                break;
            case 4:
                w = "周四"
                break;
            case 5:
                w = "周五"
                break;
            case 6:
                w = "周六"
                break;
            default:
                w = ""
        }
        var dd = {};
        dd.day = time2;
        dd.week = w;
        dd.date=time3.toString();
        return dd;
    }
    function judgeDate(date) {
		for(i in array){
		    console.log(array[i])
            console.log(date)
		    if(array[i]==date){
		        return 1;
			}
		}
		return 0;
    }
    var doctor_order = '<td rowspan="4" class="doctor_order"><table border="0" id="tab2"><tr>';
    for(var i = 0; i < d; i++) {
        if(i == 0) {
            doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">今天</div></td>'
        } else if(i == 1) {
            doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">明天</div></td>'
        } else if(i == 2) {
            doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">后天</div></td>'
        } else {
            doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">' + fun_date(i).week + '</div></td>'
        }
    }
    doctor_order+='</tr>';
    for (var j = 0;j<3;j++) {
        doctor_order+='<tr>';
        for (var k = 0;k<d;k++) {
            console.log(judgeDate(fun_date(k).date))
                if (judgeDate(fun_date(k).date)==1) {
                    doctor_order += '<td><a href = "javascript:void(0)"  onclick = "document.getElementById(\'light\').style.display=\'block\';document.getElementById(\'fade\').style.display=\'block\'"> 请点这里</a></td>'
                } else {
                    doctor_order += '<td></td>'
                }
        }
        doctor_order+='</tr>'
    }
    doctor_order+='</table></td>';
    $(".arrow_left").after(doctor_order);
    $(".doctor_order>table").width(d*140);
    //选项卡
    $(".tab_hd div").click(function() {
        var _this = $(this);
        var i = _this.index();
        _this.addClass("active");
        _this.siblings().removeClass("active");
        $(".doctor_pane").eq(i).addClass("active");
        $(".doctor_pane").eq(i).siblings().removeClass("active");
    })
    //点击左右箭头
    //动画执行完为true
    var type = true;
    function change(c){
        var left = parseInt($(".doctor_order table").css("left"));
        console.log("位移是"+left);
        left = left + (-c*140);
        if(left >0 ){
            console.log("左边没有了")
            $(".arrow_left div img").attr("src","img/left.png");
            $(".arrow_left").removeClass("left_active");
        }else if(left< (d-7)*-140){
            console.log("右边没有了")
            $(".arrow_right div img").attr("src","img/right.png");
            $(".arrow_right").removeClass("right_active");
        }else{
            console.log("aaaaaaaaaaaaaaa")
            $(".arrow_left div img").attr("src","img/left_active.png");
            $(".arrow_right div img").attr("src","img/right_active.png");
            $(".arrow_left").addClass("left_active");
            $(".arrow_right").addClass("right_active");
            $(".doctor_order table").animate({left:left+"px"},1000);
            type = true;
            console.log(type)
        }
    }

    var time = 0;
    $(".arrow_left").click(function(){
        if (time == 0) {
            time = 1; //设定间隔时间（秒）
            //启动计时器，倒计时time秒后自动关闭计时器。
            var index = setInterval(function(){
                time--;
                if (time == 0) {
                    clearInterval(index);
                }
            }, 1000);
            change(-1);
        }else{
            // alert('目前按钮事件不允许被触发');
        }


    })

    $(".arrow_right").click(function(){
        if (time == 0) {
            time = 1; //设定间隔时间（秒）
            //启动计时器，倒计时time秒后自动关闭计时器。
            var index = setInterval(function(){
                time--;
                if (time == 0) {
                    clearInterval(index);
                }
            }, 1000);
            change(1);
        }else{
            // alert('目前按钮事件不允许被触发');
        }

    })
})</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
</body>
</html>