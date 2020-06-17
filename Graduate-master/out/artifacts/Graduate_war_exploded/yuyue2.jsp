<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/4/25
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.starry.entity.Sch" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.DateUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/ui.css">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>医生坐诊时间表</title>
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
<jsp:include page="include/head.jsp"/>
<div class="hd">
    <div class="hd_bg">
        <img src="images/bg.png">
    </div>
    <div class="hd_con">
        <div class="con clearfix">
            <div class="hd_head float-left">
                <img src="${doctor.image}">
            </div>
            <div class="hd_info float-left">
                <div class="doctor_name">
                    ${doctor.name}
                </div>
                <div class="doctor_address">
                    所属科室：${depart.dName}
                </div>
                <div class="doctor_speciality">
                    职称： ${doctor.dResume}
                </div>
                <%--<div class="doctor_experience">
                    医生简介： ${doctor.dInfo}
                </div>--%>
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
                <div class="float-left"><a href="/getdoccomment?docid=${doctor.dNumber}&status=1" target="_self">就诊评价</a></div>
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
                            <%
                                List<Sch> schs= (List<Sch>) request.getAttribute("schs");
                                Sch[] q=new Sch[schs.size()];
                                schs.toArray(q);
                                StringBuffer sb=new StringBuffer();
                                sb.append("<td rowspan=\"4\" class=\"doctor_order\"> <table border=\"0\" id=\"tab2\"><tr>");
                                for(int i=0;i<7;i++){
                                    sb.append("<td><div class=\"time\">");
                                    sb.append(new DateUtils().getDate(i));
                                    sb.append("</div><div class=\"week\">");
                                    sb.append(new DateUtils().getDays(i));
                                    sb.append("</div></td>");
                                }
                                sb.append("</tr>");
                                List<String> s=new ArrayList<>();
                                for(Sch sc:schs){
                                    s.add(sc.getsTime().split(" ")[0]);
                                }
                                for(int j=0;j<1;j++){
                                    for(int k=0;k<7;k++){
                                        if(s.contains(new DateUtils().getDate(k))){
                                            sb.append("<td><a id=\"btn\" href = \"./getSchePeriod?schid=");
                                            sb.append(q[s.indexOf(new DateUtils().getDate(k))].getsNumber());
                                            sb.append("\" onclick = \"judgeuser\"> 点击预约</a></td>\"");
                                        }else {
                                            sb.append("<td></td>");
                                        }
                                    }
                                    sb.append("</tr>");
                                }
                                sb.append("</table></td>");
                                out.write(sb.toString());
                            %>
                            <td rowspan="4" class="arrow_right right_active"><div><img src="images/right_active.png"></div></td>
                        </tr>
                        <tr>
                            <td class="tb_header"></td>
                        </tr>
                       <%-- <tr>
                            <td class="tb_header">下午</td>
                        </tr>
                        <tr>
                            <td class="tb_header">夜诊</td>
                        </tr>--%>
                    </table>
                </div>
                <div>
                </div>
                <a id="doctor_evaluate" class="doctor_pane">
                    <a herf="/getdoccomment?docid=${doctor.dNumber}&status=1"></a>
                </a>
                </div>
            </div>
        </div>
    </div>
<<%--div>
    <iframe scrolling="auto" rameborder="0" src="pinglun.html" name="right" width="100%" height="100%"></iframe>
</div>--%>
<%--<script type="text
</div>/javascript" src="js/script.js"></script>--%>
<script>
    $(function() {
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
            }else if(left < (d-7)*-140){
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
