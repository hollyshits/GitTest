<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/4/10
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>H医院预约挂号平台</title>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/ui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
</head>
<body>
<jsp:include page="include/head.jsp"/>
<%--<div id="top" class="top">
    <div class="wrap">
        <p class="call">010-114/116114电话预约</p>
        <p class="welcome">
            <c:choose>
                <c:when test="${aid!=null}"><a href="index2.jsp">欢迎${aid}</a></c:when>
                <c:otherwise>欢迎来到城市医院预约挂号统一平台&nbsp;请&nbsp;
                    <a href="/login2.jsp">登录</a>
                    <a href="/register.jsp">注册</a>
                </c:otherwise>
            </c:choose>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#">帮助中心</a>
        </p>
    </div>
</div>--%>


<div id="header" class="header">
    <div class="wrap clearfix">
        <a class="logo" href="index.html"><img src="./images/logo.jpg"></a>

        <div class="search ui-search">

            <div class="ui-search-selected">医院</div>
            <div class="ui-search-select-list">
                <a href="#1">科室</a>
                <a href="#1">疾病</a>
                <a href="#1">医院</a>
            </div>
            <input type="text" name="search-content" class="ui-search-input" placeholder="请输入搜索内容">
            <a href="#" class="ui-search-submit">&nbsp;</a>

        </div>
    </div>

</div>

<div id="nav" class="nav">
    <div class="wrap">

        <div class="link menu">全部科室
            <div class="menu-list ui-menu">

                <div class="ui-menu-item">

                    <a href="#1" class="ui-menu-item-department">内科</a>
                    <a href="#1" class="ui-menu-item-disease">高血压</a>
                    <a href="#1" class="ui-menu-item-disease">冠心病</a>

                    <div class="ui-menu-item-detail">

                        <div class="ui-menu-item-detail-group">
                            <div class="ui-menu-item-detail-group-caption">
                                内科
                            </div>
                            <div class="ui-menu-item-detail-group-list">
                                <a href="#1">心脑血管科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                            </div>
                        </div>
                        <div class="ui-menu-item-detail-group">
                            <div class="ui-menu-item-detail-group-caption">
                                内科常见
                            </div>
                            <div class="ui-menu-item-detail-group-list">
                                <a href="#1">心脑血管科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                            </div>
                        </div>



                    </div>

                </div>
                <div class="ui-menu-item">

                    <a href="#1" class="ui-menu-item-department">外科</a>
                    <a href="#1" class="ui-menu-item-disease">a疾病</a>
                    <a href="#1" class="ui-menu-item-disease">b疾病</a>

                    <div class="ui-menu-item-detail">

                        <div class="ui-menu-item-detail-group">
                            <div class="ui-menu-item-detail-group-caption">
                                a疾病
                            </div>
                            <div class="ui-menu-item-detail-group-list">
                                <a href="#1">心脑血管科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                            </div>
                        </div>
                        <div class="ui-menu-item-detail-group">
                            <div class="ui-menu-item-detail-group-caption">
                                b疾病
                            </div>
                            <div class="ui-menu-item-detail-group-list">
                                <a href="#1">心脑血管科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                            </div>
                        </div>



                    </div>

                </div>
                <div class="ui-menu-item">

                    <a href="#1" class="ui-menu-item-department">儿科</a>
                    <a href="#1" class="ui-menu-item-disease">c疾病</a>
                    <a href="#1" class="ui-menu-item-disease">d疾病</a>

                    <div class="ui-menu-item-detail">

                        <div class="ui-menu-item-detail-group">
                            <div class="ui-menu-item-detail-group-caption">
                                c疾病
                            </div>
                            <div class="ui-menu-item-detail-group-list">
                                <a href="#1">心脑血管科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                            </div>
                        </div>
                        <div class="ui-menu-item-detail-group">
                            <div class="ui-menu-item-detail-group-caption">
                                d疾病
                            </div>
                            <div class="ui-menu-item-detail-group-list">
                                <a href="#1">心脑血管科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">心血管内科</a>
                                <a href="#1">神经内科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                                <a href="#1">内分泌科</a>
                                <a href="#1">血液科</a>
                            </div>
                        </div>



                    </div>

                </div>
            </div>
        </div>

        <a href="./getAllDepart" class="link">按科室挂号</a>
        <a href="./getAllDoctors" class="link">按医生挂号</a>
        <%--<a href="#" class="link">按疾病挂号</a>--%>
        <a href="#" class="link">最新公告</a>
        <a href="#" class="link right">社会知名医生</a>

    </div>
</div>


<div id="banner" class="banner">

    <div class="banner-slider ui-slider">

        <div class="ui-slider-wrap">
            <a href="#0" class="item"><img src="./images/banner_1.jpg" alt="banner-1"></a>
            <a href="#1" class="item"><img src="./images/banner_2.jpg" alt="banner-1"></a>
            <a href="#2" class="item"><img src="./images/banner_3.jpg" alt="banner-1"></a>
        </div>

        <div class="ui-slider-arrow">
            <a href="#l" class="item left">&nbsp;</a>
            <a href="#r" class="item right">&nbsp;</a>
        </div>

        <div class="ui-slider-process">
            <a href="#0" class="item item_focus">&nbsp;</a>
            <a href="#1" class="item item">&nbsp;</a>
            <a href="#2" class="item item">&nbsp;</a>
        </div>

    </div>
    <div class="banner-search">
        <div class="caption"><span class="text">快速预约</span></div>
        <div class="form ui-cascading">

            <%--div class="line">
                <select name="area" data-search="getDistinctArea" data-where=""><option>医院地区</option></select>
            </div>--%>
            <%--<div class="line">
                <select name="level" data-search="getLeveByArea" data-where=""><option>医院等级1</option>
                    <option>医院等级2</option>
                </select>

            </div>--%>
            <div class="line">
                <select name="name"  data-search="getNameByAreaAndLevel" data-where=""><option>科室名称</option></select>
            </div>
            <div class="line">
                <select name="department"  data-search="getDepartmentArrByHospitalName" data-where=""><option>医生名称</option></select>
            </div>

        </div>
        <div class="submit">
            <input type="button" class="button" value="快速查询">
        </div>

    </div>
    <div class="banner-help">

        <div class="caption"><span class="text">帮助中心</span></div>
        <div class="list">

            <a href="#" class="link">账号指南</a>
            <a href="#" class="link">预约指南</a>
            <a href="#" class="link">账号找回</a>
            <a href="#" class="link">常见问题</a>

        </div>

    </div>

</div>

<div id="content" class="content">

    <div class="wrap clearfix">

        <div class="content-tab">

            <div class="caption">
                <a href="#8" class="item item_focus">推荐科室</a>
                <a href="#7" class="item ">推荐医生</a>
            </div>
            <div class="block">
                <div class="item">
                    <div class="block-caption">

                        <a href="#1" class="block-caption-item block-caption-item_focus">全部</a>
                       <%-- <a href="#1" class="block-caption-item">东城区</a>
                        <a href="#1" class="block-caption-item">西城区</a>
                        <a href="#1" class="block-caption-item">朝阳区</a>
                        <a href="#1" class="block-caption-item">丰台区</a>
                        <a href="#1" class="block-caption-item">石景山区</a>
                        <a href="#1" class="block-caption-item">海淀区</a>
                        <a href="#1" class="block-caption-item">门头沟区</a>
                        <a href="#1" class="block-caption-item">房山区</a>
                        <a href="#1" class="block-caption-item">其他</a>
--%>
                    </div>
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $.ajax({
                                url:"${pageContext.request.contextPath}/userrecommendDoc?uname="+${id},
                                data:"",
                                success:function (data) {
                                    console.log(data);
                                    var html = "";
                                    for (var i=0;i<4;i++) {
                                        html +=" <div class=\"item\"><img src= \" "+data[i].image+ "\"alt=\"xx科室\" ／><div class=\"item-name\" ><a href='getdoc?doctornum="+data[i].dNumber+"'>"+data[i].name+"</a><span class=\"item-level\">【三级甲等】</span></div>" +
                                            "                                    <div class=\"item-phone\">"+data[i].dTel+"</div><div class=\"item-address\">医生职称："+data[i].dResume+"</div> </div>";
                                    }
                                    $("#recommend").html(html);
                                }
                            })
                            $.ajax({
                                url:"${pageContext.request.contextPath}/userrecommendDepart?uname="+${id},
                                data:"",
                                success:function (data) {
                                    console.log(data);
                                    var html = "";
                                    for (var i=0;i<4;i++) {
                                        html +=" <div class=\"item\"><img src= \"images/hospital-1.jpg\" alt=\"xx科室\" ／><div class=\"item-name\" ><a href='getdocByDepart?dNumber="+data[i].cNumber+"'>"+data[i].dName+"</a><span class=\"item-level\">【三级甲等】</span></div>" +
                                            "                                    <div class=\"item-phone\">"+data[i].dDec+"</div><div class=\"item-address\">医生数量："+data[i].docnum+"</div> </div>";
                                    }
                                    $("#recommendDepart").html(html);
                                }
                            })
                        })

                    </script>
                    <div class="block-content">
                        <div class="block-wrap">
                            <div id="recommendDepart" class="block-list clearfix">
                                <div  class="item">
                                    <img src="images/hospital-1.jpg" alt="xx科室" ／>
                                    <div class="item-name" href="#">心脑科<span class="item-level">【三级甲等】</span></div>
                                    <div class="item-phone">无</div>
                                    <div class="item-address">医生数量：3</div>
                                </div>
                                <div class="item">
                                    <img src="images/hospital-1.jpg" alt="xx科室" ／>
                                    <div class="item-name">眼科<span class="item-level">【三级甲等】</span></div>
                                    <div class="item-phone">暂无描述</div>
                                    <div class="item-address">医生数量：5</div>
                                </div>
                                <div class="item">
                                    <img src="images/hospital-1.jpg" alt="xx科室" ／>
                                    <div class="item-name">不孕不育科<span class="item-level">【三级甲等】</span></div>
                                    <div class="item-phone">不孕不育作为医院的重点科室，率先引进世界高成功率技术！</div>
                                    <div class="item-address">医生数量：1</div>
                                </div>
                                <div class="item">
                                    <img src="images/hospital-1.jpg" alt="xx医院" ／>
                                    <div class="item-name">口腔科<span class="item-level">【三级甲等】</span></div>
                                    <div class="item-phone">种植中心、正畸中心、修复等是特色专科。</div>
                                    <div class="item-address">医生数量：1</div>
                                </div>
                            </div>
                            <div class="block-text-list clearfix">

                                <a href="#9" class="item">首都儿科研究所附属科室 <span class="level">【三级甲等】</span></a>

                                <a href="#9" class="item">首都妇科研究所附属儿童科室 <span class="level">【三级甲等】</span></a>
                                <a href="#9" class="item">首都精神科研究所附属儿童科室 <span class="level">【三级甲等】</span></a>
                                <a href="#9" class="item">首都儿科研究所附属儿童科室 <span class="level">【三级甲等】</span></a>
                                <a href="#9" class="item">首都儿科研究所附属儿童科室 <span class="level">【三级甲等】</span></a>
                                <a href="#9" class="item">首都儿科研究所附属儿童科室 <span class="level">【三级甲等】</span></a>

                            </div>
                            <a class="block-more">更多科室</a>
                        </div>

                        <div class="block-wrap" style="display: none;">
                            其他城区内容
                        </div>

                    </div>
                </div>
                <div class="item" style="display: none;">
                    <div class="item">
                        <div class="block-caption">

                            <a href="#1" class="block-caption-item block-caption-item_focus">全部</a>
                            <%-- <a href="#1" class="block-caption-item">东城区</a>
                             <a href="#1" class="block-caption-item">西城区</a>
                             <a href="#1" class="block-caption-item">朝阳区</a>
                             <a href="#1" class="block-caption-item">丰台区</a>
                             <a href="#1" class="block-caption-item">石景山区</a>
                             <a href="#1" class="block-caption-item">海淀区</a>
                             <a href="#1" class="block-caption-item">门头沟区</a>
                             <a href="#1" class="block-caption-item">房山区</a>
                             <a href="#1" class="block-caption-item">其他</a>
     --%>
                        </div>
                        <div class="block-content">

                            <div class="block-wrap">
                                <div id="recommend" class="block-list clearfix">

                                    <div class="item">
                                        <img src="images/hospital-1.jpg" alt="xx科室" ／>
                                        <div class="item-name" href="#">心脑科<span class="item-level">【三级甲等】</span></div>
                                        <div class="item-phone">无</div>
                                        <div class="item-address">医生数量：3</div>
                                    </div>
                                    <div class="item">
                                        <img src="images/hospital-1.jpg" alt="xx科室" ／>
                                        <div class="item-name">眼科<span class="item-level">【三级甲等】</span></div>
                                        <div class="item-phone">暂无描述</div>
                                        <div class="item-address">医生数量：5</div>
                                    </div>
                                    <div class="item">
                                        <img src="images/hospital-1.jpg" alt="xx科室" ／>
                                        <div class="item-name">不孕不育科<span class="item-level">【三级甲等】</span></div>
                                        <div class="item-phone">不孕不育作为医院的重点科室，率先引进世界高成功率技术！</div>
                                        <div class="item-address">医生数量：1</div>
                                    </div>
                                    <div class="item">
                                        <img src="images/hospital-1.jpg" alt="xx医院" ／>
                                        <div class="item-name">口腔科<span class="item-level">【三级甲等】</span></div>
                                        <div class="item-phone">种植中心、正畸中心、修复等是特色专科。</div>
                                        <div class="item-address">医生数量：1</div>
                                    </div>
                                </div>
                                <div class="block-text-list clearfix">

                                    <a href="#9" class="item">首都儿科研究所附属儿童医院 <span class="level">【三级甲等】</span></a>

                                    <a href="#9" class="item">首都儿科研究所附属儿童医院 <span class="level">【三级甲等】</span></a>
                                    <a href="#9" class="item">首都儿科研究所附属儿童医院 <span class="level">【三级甲等】</span></a>
                                    <a href="#9" class="item">首都儿科研究所附属儿童医院 <span class="level">【三级甲等】</span></a>
                                    <a href="#9" class="item">首都儿科研究所附属儿童医院 <span class="level">【三级甲等】</span></a>
                                    <a href="#9" class="item">首都儿科研究所附属儿童医院 <span class="level">【三级甲等】</span></a>

                                </div>
                                <a class="block-more">更多医生</a>
                            </div>

                            <div class="block-wrap" style="display: none;">
                                其他医生内容
                            </div>

                        </div>
                    </div>
                </div>
            </div>


        </div>

        <div class="content-news">
            <div class="caption"> 最新公告 <a href="#8" class="more">|更多</a> </div>
            <div class="list">

                <a href="#9" class="link">医院特需门诊暂停更新号源通...</a>
                <a href="#9" class="link">防护策略升级通知</a>
                <a href="#9" class="link">医院特需门诊暂停更新号源通...</a>
                <a href="#9" class="link">防护策略升级通知</a>
                <a href="#9" class="link">医院特需门诊暂停更新号源通...</a>
                <a href="#9" class="link">防护策略升级通知</a>

            </div>
        </div>
        <div class="content-close">

            <div class="caption"> 停诊公告 <a href="#8" class="more">|更多</a> </div>
            <div class="list">

                <a href="#9" class="link">首都医科大学附属北京安贞医院消...</a>
                <a href="#9" class="link">首都医科大学附属北京安贞医院妇</a>
                <a href="#9" class="link">医院特需门诊暂停更新号源通...</a>
                <a href="#9" class="link">医院妇科就诊规范...</a>
                <a href="#9" class="link">医院特需门诊暂停更新号源通...</a>
                <a href="#9" class="link">医院儿科就诊规范</a>
            </div>
        </div>
    </div>

</div>

<div id="footer" class="footer">
    Copyright © Lyx
</div>
<a href="#" class="go-top"></a>
<script type="text/javascript" src="js/ui.js"></script>
<script type="text/javascript" src="js/data.js"></script>
</body>
</html>
