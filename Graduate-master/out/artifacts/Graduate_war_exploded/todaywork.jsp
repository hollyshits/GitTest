<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/5/12
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/jQuery/jquery-3.1.1.min.js"></script>
    <script src="static/js/bootstrap-dropdown.min.js"></script>
    <script src="static/js/reader.js"></script>

    <script src="ajax-lib/ajaxutils.js"></script>
    <script src="static/js/readerUpdateInfo.js"></script>
    <script src="static/js/readerUpdatePwd.js"></script>
    <link rel="stylesheet" type="text/css" href="css/selectFilter.css" />
    <style type="text/css">
        body {
            padding: 30px;
        }

        .item {
            width: 240px;
            height: 32px;
            margin: 100px auto;
        }
    </style>
</head>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<body class="bootstrap-admin-with-small-navbar">
<!-- 判断是否已经登录 -->

<!-- content -->
<div class="col-md-10">
    <div class="row">
    </div>


    <c:choose>
        <c:when test="${orders.size()==0}">
            <textarea  class="btn btn-info btn-xs" data-toggle="modal" >今日暂无工作安排</textarea>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-lg-12">
                    <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>序列号</th>
                            <th>预约单号</th>
                            <th>预约单日期</th>
                            <th>预约时间段</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <c:forEach items="${orders}" var="pv">
                            <td> ${pv.oNumber}</td>
                            <td>${pv.sNumber}</td>
                            <td>${pv.oTime}</td>
                            <td>${pv.oTime}</td>
                            <%--<div class="filter-box">
                            <div class="filter-text">
                                <input class="filter-title" type="text" readonly placeholder="pleace select" />
                                <i class="icon icon-filter-arrow"></i>
                            </div>
                            <select name="filter">
                                <option value="new" disabled>已预约</option>
                                <option value="unaudited">未预约</option>
                                <option value="nothrough" selected>未通过</option>
                            </select>
                        </div>--%>
                            <td>
                                <c:choose>
                                    <c:when test="${pv.status==1}">
                                        <button type="button" class="btn btn-info btn-xs" data-toggle="modal" onclick="changestate(${pv.oNumber},4)" >就诊</button>
                                        <button type="button" class="btn btn-info btn-xs" data-toggle="modal" onclick="changestate(${pv.oNumber},5)" >未赴约</button>
                                    </c:when>
                                    <c:when test="${pv.status==5}">
                                        <button type="button" disabled class="btn btn-info btn-xs" data-toggle="modal" onclick="changestate(${pv.oNumber},4)" >未赴约</button>
                                    </c:when>
                                    <c:otherwise>
                                        <a disabled="" href="#">就诊完毕</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            </tbody>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </c:otherwise>
    </c:choose>




    <script type="text/javascript">
        function changestate(oNumber,status) {
                location.href = "/changeOrderStatus?oNumber="+oNumber+"&status="+status;
        }
        function getPv(sid,timeDiv) {
            location.href = "/getorder?sid="+sid+"&timeDiv="+timeDiv;
        }
    </script>

    <script type="text/javascript">
        function getinfo(did) {
            location.href = "/Graduate/borrowServlet?did="+did;
        }
    </script>
</div>



<%--<div>
    <iframe scrolling="auto" rameborder="0" src="./getAllNew?pageNum=1&pageSize=3" name="bottom" width="100%" height="100%"></iframe>
</div>--%>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/selectFilter.js"></script>
</body>
</html>
