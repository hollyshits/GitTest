<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/5/4
  Time: 16:01
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
</head>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<body class="bootstrap-admin-with-small-navbar">
<!-- 判断是否已经登录 -->

<!-- content -->
<div class="col-md-10">
   <%-- <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default bootstrap-admin-no-table-panel">
                <div class="panel-heading">
                    <div class="text-muted bootstrap-admin-box-title">查询</div>
                </div>
                <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                    <form class="form-horizontal" action="/selectServlet" method="post">
                        <input type="hidden" name="tip" value="2">
                        <div class="col-lg-8 form-group">
                            <label class="col-lg-4 control-label" for="query_bname">医生名称</label>
                            <div class="col-lg-8">
                                <input class="form-control" id="bookName" name="name" type="text" value="">
                                <label class="control-label" for="query_bname" style="display: none;"></label>
                            </div>
                        </div>


                        <div class="col-lg-4 form-group">

                            <button type="submit" class="btn btn-primary" id="btn_query" >查询</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>--%>

    <div class="row">
        <div class="col-lg-12">
            <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>挂号单</th>
                    <th>科室名称</th>
                    <th>剩余挂号数</th>
                    <th>价格</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>操作</th>
                </tr>
                </thead>

                <c:forEach items="${docScheList}" var="docSche">
                <td> ${docSche.sNumber}</td>
                <td>${Dname}</td>
                <td>${docSche.rest}</td>
                <td>${docSche.price}</td>
                <td>${docSche.sTime}</td>
                <td>${docSche.eTime}</td>
                <td><button type="button" class="btn btn-info btn-xs" data-toggle="modal" onclick="/*borrowbook(${docSche.getsNumber()})*/" >确定</button>
                    <button type="button" class="btn btn-info btn-xs" data-toggle="modal" target="bottom" onclick="getPv(${docSche.getsNumber()})" >查看详细信息</button>  	</td>
                    </tbody>
                </c:forEach>
            </table>



        </div>
    </div>

    <script type="text/javascript">
        function borrowbook(bid) {
            con=confirm("是否修改?");
            if(con==true){
                location.href = "/Graduate/borrowServlet?tip=1&bid="+bid;
            }
        }
        function getPv(sid) {
            location.href = "/getdocPv?sid="+sid;
        }
    </script>

    <script type="text/javascript">
        function getinfo(did) {
            location.href = "/Graduate/borrowServlet?did="+did;
        }
    </script>
</div>




<div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="infoModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" id="div_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>